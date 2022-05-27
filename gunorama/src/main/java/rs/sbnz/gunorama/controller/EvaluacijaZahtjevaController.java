package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.dto.DozvolaUpitnik;
import rs.sbnz.gunorama.dto.KorisnikDTO;
import rs.sbnz.gunorama.dto.ZahtjevDTO;
import rs.sbnz.gunorama.model.Zahtjev;
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
    public ZahtjevDTO evaluateQuestionnaire(@RequestBody DozvolaUpitnik questionnaire) {
        Zahtjev zahtjev = evaluacijaZahtjevaService.evaluateQuestionnaire(questionnaire);
        return new ZahtjevDTO(new KorisnikDTO(zahtjev.getKorisnik().getEmail()), zahtjev.isOdobren(), zahtjev.getRazlogOdbijanja(), zahtjev.getDomenPrimjene(), zahtjev.getKonkretnaNamjena(), zahtjev.getDokumenti());
    }
}
