package rs.sbnz.gunorama.controller;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.facts.GradjaninZdravstvenoSposobanFact;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;

@RestController
@RequestMapping(value = "/api/zdravstveno-sposoban")
public class ZdravstvenoSposobanController {

    private final KieContainer kieContainer;


    @Autowired
    public ZdravstvenoSposobanController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }


    @GetMapping
    public ZdravstvenoSposobanFaza doSomeStuff() {
        Zahtjev zahtjev = new Zahtjev();
        zahtjev.setId(1);

        ZdravstvenoSposobanFaza zdravstvenoSposobanFaza = new ZdravstvenoSposobanFaza();
        zdravstvenoSposobanFaza.setDioptrija(-0.5);
        zdravstvenoSposobanFaza.setFizickiSposoban(false);
        zdravstvenoSposobanFaza.setZahtjevId(1);
        zdravstvenoSposobanFaza.setProsaoPsihijatrijskuEvaluaciju(true);
        zdravstvenoSposobanFaza.setProsaoPsiholoskuEvaluaciju(true);


        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(zahtjev);
        kieSession.insert(zdravstvenoSposobanFaza);
        kieSession.fireAllRules();
        kieSession.dispose();
        return zdravstvenoSposobanFaza;
    }
}
