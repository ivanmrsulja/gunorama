package rs.sbnz.gunorama.service;

import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.Korisnik;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.facts.GradjaninZdravstvenoSposobanFact;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ZdravstvenoSposobanService {

    private final KieContainer kieContainer;

    @Autowired
    public ZdravstvenoSposobanService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public GradjaninZdravstvenoSposobanFact evaluateQuestionnaire(ZdravstvenoSposobanFaza questionnaire) {
        Zahtjev zahtjev = new Zahtjev();
        zahtjev.setId(1);

        Korisnik k = new Korisnik(1, "email", "passw");
        zahtjev.setKorisnik(k);

        questionnaire.setZahtjevId(zahtjev.getId());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(zahtjev);
        kieSession.insert(questionnaire);
        kieSession.getAgenda().getAgendaGroup("Zdravstvena evaluacija").setFocus();
        kieSession.fireAllRules();
        Collection<GradjaninZdravstvenoSposobanFact> myFacts = (Collection<GradjaninZdravstvenoSposobanFact>) kieSession.getObjects( new ClassObjectFilter(GradjaninZdravstvenoSposobanFact.class) );
        kieSession.dispose();
        return myFacts.stream().filter((fact) -> fact.getZahtjevId().equals(zahtjev.getId())).collect(Collectors.toList()).get(0);
    }
}
