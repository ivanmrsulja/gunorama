package rs.sbnz.gunorama.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.dto.DozvolaUpitnik;
import rs.sbnz.gunorama.model.Dokument;
import rs.sbnz.gunorama.model.Korisnik;
import rs.sbnz.gunorama.model.PotrebanUslov;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.enums.TipDokumenta;
import rs.sbnz.gunorama.model.faze.DomenPrimjeneFaza;
import rs.sbnz.gunorama.model.faze.SpecificniZahtjeviFaza;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;
import rs.sbnz.gunorama.repository.KorisnikRepository;
import rs.sbnz.gunorama.repository.PotrebanUslovRepository;
import rs.sbnz.gunorama.repository.ZahtjevRepository;
import rs.sbnz.gunorama.util.PasswordGenerator;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EvaluacijaZahtjevaService {

    private final KieContainer kieContainer;

    private final PotrebanUslovRepository potrebanUslovRepository;

    private final ZahtjevRepository zahtjevRepository;

    private final KorisnikRepository korisnikRepository;

    private final Map<TipDokumenta, Integer> prioritetDokumenta = new HashMap<>(Map.of(
            TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU, 1,
            TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU, 2,
            TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKO_STRELJACKOM_DRUSTVU, 1,
            TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA, 1,
            TipDokumenta.DOKAZ_O_CLANSTVU_U_STRELJACKOM_KLUBU, 1,
            TipDokumenta.DOKAZ_O_POLOZENOJ_OBUCI_ZA_RUKOVANJE_ORUZJEM, 1,
            TipDokumenta.DOKAZ_O_UGROZENOSTI_LICNE_BEZBIJEDNOSTI, 100,
            TipDokumenta.DOKAZ_O_PLACENOJ_CLANARINI_U_LOVACKOM_ILI_STRELJACKOM_KLUBU, 0,
            TipDokumenta.LICNA_KARTA, 0,
            TipDokumenta.DOKAZ_O_UPLACENIM_NEOPHODNIM_TAKSAMA, 0
            ));

    @Autowired
    public EvaluacijaZahtjevaService(KieContainer kieContainer,
                                     PotrebanUslovRepository potrebanUslovRepository,
                                     ZahtjevRepository zahtjevRepository,
                                     KorisnikRepository korisnikRepository) {
        this.kieContainer = kieContainer;
        this.potrebanUslovRepository = potrebanUslovRepository;
        this.zahtjevRepository = zahtjevRepository;
        this.korisnikRepository = korisnikRepository;
    }

    @Transactional
    public Zahtjev evaluateQuestionnaire(DozvolaUpitnik questionnaire) {
        Zahtjev zahtjev = zahtjevRepository.save(new Zahtjev());
        zahtjev = zahtjevRepository.getById(zahtjev.getId());

        Korisnik k;
        Optional<Korisnik> optionalKorisnik = korisnikRepository.findOneByEmail(questionnaire.getEmailKorisnika());
        k = optionalKorisnik.orElseGet(() -> new Korisnik(questionnaire.getEmailKorisnika(), String.valueOf(PasswordGenerator.generatePassword(12))));
        korisnikRepository.save(k);

        zahtjev.setKorisnik(k);

        ZdravstvenoSposobanFaza faza1 = new ZdravstvenoSposobanFaza(zahtjev.getId(), questionnaire.getDioptrija(), questionnaire.isProsaoPsiholoskuEvaluaciju(), questionnaire.isProsaoPsihijatrijskuEvaluaciju());

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(zahtjev);
        FactHandle faza1FactHandle = kieSession.insert(faza1);
        kieSession.getAgenda().getAgendaGroup("Zdravstvena evaluacija").setFocus();
        kieSession.fireAllRules();
        kieSession.delete(faza1FactHandle);

        List<PotrebanUslov> uslovi = potrebanUslovRepository.findAll();
        uslovi.forEach(kieSession::insert);

        List<Dokument> dokumenta = new ArrayList<>();
        for(TipDokumenta tipDokumenta : questionnaire.getDokumenta()) {
            dokumenta.add(new Dokument(tipDokumenta, prioritetDokumenta.get(tipDokumenta)));
        }

        DomenPrimjeneFaza faza2 = new DomenPrimjeneFaza(dokumenta, zahtjev.getId());
        FactHandle faza2FactHandle = kieSession.insert(faza2);
        kieSession.getAgenda().getAgendaGroup("Domen primjene").setFocus();
        kieSession.fireAllRules();

        kieSession.delete(faza2FactHandle);
        kieSession.insert(new SpecificniZahtjeviFaza(dokumenta, zahtjev.getId()));
        kieSession.getAgenda().getAgendaGroup("Specificni zahtjevi za domen primjene").setFocus();
        kieSession.fireAllRules();

        kieSession.dispose();

        return zahtjev;
    }
}
