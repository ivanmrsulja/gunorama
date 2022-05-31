package rs.sbnz.gunorama.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.events.Prekrsaj;

@Service
public class DetekcijaNepravilnostiService {

    private final KieSession kieSession;

    @Autowired
    public DetekcijaNepravilnostiService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public void detektujPrekrsaj(Prekrsaj prekrsaj) {
        kieSession.insert(prekrsaj);
        kieSession.fireAllRules();
    }
}
