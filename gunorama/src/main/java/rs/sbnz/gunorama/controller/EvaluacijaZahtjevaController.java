package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.faze.ZdravstvenoSposobanFaza;
import rs.sbnz.gunorama.service.EvaluacijaZahtjevaService;

@RestController
@RequestMapping(value = "/api/zahtjev-za-dozvolu")
public class EvaluacijaZahtjevaController {

    private final EvaluacijaZahtjevaService evaluacijaZahtjevaService;

    @Autowired
    public EvaluacijaZahtjevaController(EvaluacijaZahtjevaService evaluacijaZahtjevaService) {
        this.evaluacijaZahtjevaService = evaluacijaZahtjevaService;
    }

    @PostMapping
    public Zahtjev evaluateQuestionnaire(@RequestBody ZdravstvenoSposobanFaza questionnaire) {
        return evaluacijaZahtjevaService.evaluateQuestionnaire(questionnaire);
    }
}
