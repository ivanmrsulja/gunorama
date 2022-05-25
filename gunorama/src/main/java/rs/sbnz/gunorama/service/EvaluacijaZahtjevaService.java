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
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;
import rs.sbnz.gunorama.repository.PotrebanUslovRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public DomenPrimjeneFact evaluateQuestionnaire(ZdravstvenoSposobanFaza questionnaire) {
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
//        uslovi.forEach(kieSession::insert);
        for(PotrebanUslov uslov : uslovi) {
            kieSession.insert(uslov);
        }

        List<Dokument> dokumenta = new ArrayList<>();
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU));
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKO_STRELJACKOM_DRUSTVU));
        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_CLANSTVU_U_STRELJACKOM_KLUBU));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_POLOZENOJ_OBUCI_ZA_RUKOVANJE_ORUZJEM));
//        dokumenta.add(new Dokument(TipDokumenta.DOKAZ_O_UGROZENOSTI_LICNE_BEZJEDNOSTI));
        DomenPrimjeneFaza dpf = new DomenPrimjeneFaza(dokumenta, zahtjev.getId());
        kieSession.insert(dpf);
        kieSession.getAgenda().getAgendaGroup("Domen primjene").setFocus();
        kieSession.fireAllRules();

        Collection<DomenPrimjeneFact> domenPrimjeneFacts = (Collection<DomenPrimjeneFact>) kieSession.getObjects( new ClassObjectFilter(DomenPrimjeneFact.class) );

        kieSession.dispose();
        return domenPrimjeneFacts.stream().filter(fact -> fact.getZahtjevId().equals(zahtjev.getId())).collect(Collectors.toList()).get(0);
    }
}
