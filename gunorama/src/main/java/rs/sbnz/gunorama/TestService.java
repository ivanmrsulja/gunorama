package rs.sbnz.gunorama;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.Citizen;
import rs.sbnz.gunorama.model.Item;


@Service
public class TestService {


    private final KieContainer kieContainer;

    private final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    public TestService(KieContainer kieContainer) {
        log.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
    }

    public Citizen testCitizenRule(Citizen i) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.fireAllRules();
        kieSession.dispose();
        return i;
    }

    public Item getClassifiedItem(Item i) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();
        log.info(firedRules + "");
        return i;
    }
}
