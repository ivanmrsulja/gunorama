package rs.sbnz.gunorama.service;


import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.dto.KorisnickiUpitnik;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.facts.KonkretnaNamjenaFact;
import rs.sbnz.gunorama.model.faze.KonkretnaNamjenaFaza;
import rs.sbnz.gunorama.repository.ZahtjevRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EvaluacijaUpitnikaService {

    private final KieContainer kieContainer;

    private final ZahtjevRepository zahtjevRepository;

    @Autowired
    public EvaluacijaUpitnikaService(KieContainer kieContainer, ZahtjevRepository zahtjevRepository) {
        this.kieContainer = kieContainer;
        this.zahtjevRepository = zahtjevRepository;
    }


    public KonkretnaNamjenaFact evaluate(KorisnickiUpitnik korisnickiUpitnik) {

        Zahtjev zahtjev = this.zahtjevRepository.save(new Zahtjev());
        zahtjev.setDomenPrimjene(DomenPrimjene.SAMOODBRANA);
        zahtjev.setOdobren(true);

        KonkretnaNamjenaFaza konkretnaNamjenaFaza = new KonkretnaNamjenaFaza(
                zahtjev.getId(),
                korisnickiUpitnik.getTezinaDivljaci(),
                korisnickiUpitnik.getDaljinaMete(),
                korisnickiUpitnik.getSkitIzabran(),
                korisnickiUpitnik.getTrapIzabran(),
                korisnickiUpitnik.getNosenjeIzabrano(),
                korisnickiUpitnik.getPosjedovanjeIzabrano()
        );

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(zahtjev);
        FactHandle konkretnaNamjenaFactHandle = kieSession.insert(konkretnaNamjenaFaza);
        kieSession.getAgenda().getAgendaGroup("Konkretna namjena").setFocus();

        kieSession.fireAllRules();
        kieSession.dispose();

        Collection<KonkretnaNamjenaFact> myFacts = (Collection<KonkretnaNamjenaFact>) kieSession.getObjects( new ClassObjectFilter(KonkretnaNamjenaFact.class) );
        kieSession.dispose();
        return myFacts.stream().filter((fact) -> fact.getZahtjevId().equals(zahtjev.getId())).collect(Collectors.toList()).get(0);
        //return new KonkretnaNamjenaFact();
    }
}
