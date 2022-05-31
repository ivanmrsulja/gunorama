package rs.sbnz.gunorama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.model.events.Prekrsaj;
import rs.sbnz.gunorama.service.DetekcijaNepravilnostiService;

@RestController
@RequestMapping("/api/detekcija-nepravilnosti")
public class DetekcijaNepravilnostiController {

    private final DetekcijaNepravilnostiService detekcijaNepravilnostiService;

    @Autowired
    public DetekcijaNepravilnostiController(DetekcijaNepravilnostiService detekcijaNepravilnostiService) {
        this.detekcijaNepravilnostiService = detekcijaNepravilnostiService;
    }

    @PostMapping
    public void detektujPrekrsaj(@RequestBody Prekrsaj prekrsaj) {
        detekcijaNepravilnostiService.detektujPrekrsaj(prekrsaj);
    }
}
