package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.dto.KalibarDTO;
import rs.sbnz.gunorama.dto.KorisnickiUpitnik;
import rs.sbnz.gunorama.dto.OruzjeDTO;
import rs.sbnz.gunorama.dto.PreporucenoOruzjeDTO;
import rs.sbnz.gunorama.model.facts.KonkretnaNamjenaFact;
import rs.sbnz.gunorama.model.facts.PreporucenoOruzjeFact;
import rs.sbnz.gunorama.service.EvaluacijaUpitnikaService;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/korisnicki-upitnik")
public class EvaluacijaUpitnikaController {

    private final EvaluacijaUpitnikaService evaluacijaUpitnikaService;


    @Autowired
    public EvaluacijaUpitnikaController(EvaluacijaUpitnikaService evaluacijaUpitnikaService) {
        this.evaluacijaUpitnikaService = evaluacijaUpitnikaService;
    }


    @PostMapping
    public PreporucenoOruzjeDTO evaluateUserPoll(@RequestBody KorisnickiUpitnik korisnickiUpitnik) {
        PreporucenoOruzjeFact preporucenoOruzjeFact = this.evaluacijaUpitnikaService.evaluate(korisnickiUpitnik);

        PreporucenoOruzjeDTO preporucenoOruzjeDTO = new PreporucenoOruzjeDTO();
        preporucenoOruzjeDTO.setPreporucenoOruzje(preporucenoOruzjeFact.getPreporucenoOruzje().stream().map(OruzjeDTO::new).collect(Collectors.toList()));
        preporucenoOruzjeDTO.setDozvoljeniKalibri(preporucenoOruzjeFact.getDozvoljeniKalibri().stream().map(KalibarDTO::new).collect(Collectors.toList()));
        preporucenoOruzjeDTO.setKonkretnaNamjena(preporucenoOruzjeFact.getKonkretnaNamjena());

        return preporucenoOruzjeDTO;
    }
}
