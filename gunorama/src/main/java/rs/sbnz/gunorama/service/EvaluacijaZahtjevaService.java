package rs.sbnz.gunorama.service;

import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.Dokument;
import rs.sbnz.gunorama.model.Korisnik;
import rs.sbnz.gunorama.model.PotrebanUslov;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.enums.TipDokumenta;
import rs.sbnz.gunorama.model.facts.DomenPrimjeneFact;
import rs.sbnz.gunorama.model.faze.DomenPrimjeneFaza;
import rs.sbnz.gunorama.model.faze.SpecificniZahtjeviFaza;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;
import rs.sbnz.gunorama.repository.PotrebanUslovRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EvaluacijaZahtjevaService {

    private final KieContainer kieContainer;

    private final PotrebanUslovRepository potrebanUslovRepository;

    @Autowired
    public EvaluacijaZahtjevaService(KieContainer kieContainer,
                                     PotrebanUslovRepository potrebanUslovRepository) {
        this.kieContainer = kieContainer;
        this.potrebanUslovRepository = potrebanUslovRepository;
    }

    public Zahtjev evaluateQuestionnaire(ZdravstvenoSposobanFaza questionnaire) {
        Zahtjev zahtjev = new Zahtjev();
        zahtjev.setId(1);

        Korisnik k = new Korisnik(1, "email", "passw");
        zahtjev.setKorisnik(k);

        questionnaire.setZahtjevId(zahtjev.getId());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(zahtjev);
        FactHandle questionnaireFactHandle = kieSession.insert(questionnaire);
        kieSession.getAgenda().getAgendaGroup("Zdravstvena evaluacija").setFocus();
        kieSession.fireAllRules();
        kieSession.delete(questionnaireFactHandle);

        List<PotrebanUslov> uslovi = potrebanUslovRepository.findAll();
        uslovi.forEach(kieSession::insert);

        List<Dokument> dokumenta = new ArrayList<>();
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU, 1));
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU, 2));
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKO_STRELJACKOM_DRUSTVU, 1));
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA, 1));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_STRELJACKOM_KLUBU, 1));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_POLOZENOJ_OBUCI_ZA_RUKOVANJE_ORUZJEM, 1));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_UGROZENOSTI_LICNE_BEZJEDNOSTI, 100));

        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_PLACENOJ_CLANARINI_U_LOVACKOM_ILI_STRELJACKOM_KLUBU, 0));
//        dokumenta.add(new Dokument(TipDokumenta.LICNA_KARTA, 0));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_UPLACENIM_NEOPHODNIM_TAKSAMA, 0));

        DomenPrimjeneFaza dpf = new DomenPrimjeneFaza(dokumenta, zahtjev.getId());
        FactHandle domenPrimjeneFactHandle = kieSession.insert(dpf);
        kieSession.getAgenda().getAgendaGroup("Domen primjene").setFocus();
        kieSession.fireAllRules();
        Collection<DomenPrimjeneFact> domenPrimjeneFacts = (Collection<DomenPrimjeneFact>) kieSession.getObjects( new ClassObjectFilter(DomenPrimjeneFact.class) );

        kieSession.delete(domenPrimjeneFactHandle);
        kieSession.insert(new SpecificniZahtjeviFaza(dokumenta, zahtjev.getId()));
        kieSession.getAgenda().getAgendaGroup("Specificni zahtjevi za domen primjene").setFocus();
        kieSession.fireAllRules();

        kieSession.dispose();
        return zahtjev;
    }
}
