package rs.sbnz.gunorama.controller;


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

    private final ZdravstvenoSposobanService zdravstvenoSposobanService;

    @Autowired
    public ZdravstvenoSposobanController(ZdravstvenoSposobanService zdravstvenoSposobanService) {
        this.zdravstvenoSposobanService = zdravstvenoSposobanService;
    }

    @PostMapping
    public GradjaninZdravstvenoSposobanFact evaluateQuestionnaire(@RequestBody ZdravstvenoSposobanFaza questionnaire) {
        return zdravstvenoSposobanService.evaluateQuestionnaire(questionnaire);
    }
}
