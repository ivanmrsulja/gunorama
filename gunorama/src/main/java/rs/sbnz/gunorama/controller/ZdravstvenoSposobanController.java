package rs.sbnz.gunorama.controller;


import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.model.facts.GradjaninZdravstvenoSposobanFact;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;
import rs.sbnz.gunorama.service.ZdravstvenoSposobanService;

@RestController
@RequestMapping(value = "/api/zdravstveno-sposoban")
public class ZdravstvenoSposobanController {

    private final KieContainer kieContainer;
    private final ZdravstvenoSposobanService zdravstvenoSposobanService;

    @Autowired
    public ZdravstvenoSposobanController(KieContainer kieContainer, ZdravstvenoSposobanService zdravstvenoSposobanService) {
        this.kieContainer = kieContainer;
        this.zdravstvenoSposobanService = zdravstvenoSposobanService;
    }

    @PostMapping
    public GradjaninZdravstvenoSposobanFact evaluateQuestionnaire(@RequestBody ZdravstvenoSposobanFaza questionnaire) {
        return zdravstvenoSposobanService.evaluateQuestionnaire(questionnaire);
    }
}
