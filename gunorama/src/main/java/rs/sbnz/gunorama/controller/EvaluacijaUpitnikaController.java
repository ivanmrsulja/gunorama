package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.dto.KorisnickiUpitnik;
import rs.sbnz.gunorama.model.facts.KonkretnaNamjenaFact;
import rs.sbnz.gunorama.service.EvaluacijaUpitnikaService;

@RestController
@RequestMapping(value = "/api/korisnicki-upitnik")
public class EvaluacijaUpitnikaController {

    private final EvaluacijaUpitnikaService evaluacijaUpitnikaService;


    @Autowired
    public EvaluacijaUpitnikaController(EvaluacijaUpitnikaService evaluacijaUpitnikaService) {
        this.evaluacijaUpitnikaService = evaluacijaUpitnikaService;
    }


    @PostMapping
    public KonkretnaNamjenaFact evaluateUserPoll(@RequestBody KorisnickiUpitnik korisnickiUpitnik) {
        return this.evaluacijaUpitnikaService.evaluate(korisnickiUpitnik);
    }
}
