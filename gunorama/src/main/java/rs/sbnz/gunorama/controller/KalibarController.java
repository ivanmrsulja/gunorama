package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.dto.KalibarDTO;
import rs.sbnz.gunorama.service.KalibarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/kalibri")
public class KalibarController {

    private final KalibarService kalibarService;

    @Autowired
    public KalibarController(KalibarService kalibarService) {
        this.kalibarService = kalibarService;
    }


    @GetMapping
    public List<KalibarDTO> dobaviSve() {
        return this.kalibarService.dobaviSve().stream().map(KalibarDTO::new).collect(Collectors.toList());
    }
}
