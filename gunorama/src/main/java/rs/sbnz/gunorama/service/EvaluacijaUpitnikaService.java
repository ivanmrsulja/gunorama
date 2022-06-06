package rs.sbnz.gunorama.service;


import lombok.extern.slf4j.Slf4j;
import org.drools.core.ClassObjectFilter;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.dto.KorisnickiUpitnikDTO;
import rs.sbnz.gunorama.exception.NotFoundException;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.facts.PreporucenoOruzjeFact;
import rs.sbnz.gunorama.model.faze.KonkretnaNamjenaFaza;
import rs.sbnz.gunorama.model.faze.LicnePreferenceFaza;
import rs.sbnz.gunorama.repository.KalibarRepository;
import rs.sbnz.gunorama.repository.OruzjeRepository;
import org.kie.internal.utils.KieHelper;
import rs.sbnz.gunorama.repository.ZahtjevRepository;
import rs.sbnz.gunorama.util.RuleContainer;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvaluacijaUpitnikaService {

    private final RuleService ruleService;

    private final KalibarRepository kalibarRepository;

    private final OruzjeRepository oruzjeRepository;

    private final ZahtjevRepository zahtjevRepository;

    private final RuleContainer ruleContainer;


    @Autowired
    public EvaluacijaUpitnikaService(RuleService ruleService, KalibarRepository kalibarRepository, OruzjeRepository oruzjeRepository, ZahtjevRepository zahtjevRepository, RuleContainer ruleContainer) {
        this.ruleService = ruleService;
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
        this.zahtjevRepository = zahtjevRepository;
        this.ruleContainer = ruleContainer;
    }


    public PreporucenoOruzjeFact evaluate(KorisnickiUpitnikDTO korisnickiUpitnik) {

        List<String> rules = this.ruleContainer.getRules();
        String templateRules = this.ruleService.compileOdredjivanjeKalibaraTemplate(korisnickiUpitnik.getMinimalniPrecnikUMilimetrima(), korisnickiUpitnik.getMaksimalniPrecnikUMilimetrima());

        KieHelper kieHelper = new KieHelper();
        for (String rule : rules)
            kieHelper.addContent(rule, ResourceType.DRL);

        kieHelper.addContent(templateRules, ResourceType.DRL);

        KieSession kieSession = kieHelper.build().newKieSession();

        Integer zahtjevId = korisnickiUpitnik.getZahtjevId();
        Zahtjev zahtjev = this.zahtjevRepository.findById(zahtjevId)
                .orElseThrow(() -> new NotFoundException(String.format("Zahtjev sa id-ijem: %d nije pronađen.", zahtjevId)));

        kieSession.insert(zahtjev);

        KonkretnaNamjenaFaza konkretnaNamjenaFaza = new KonkretnaNamjenaFaza(
                korisnickiUpitnik.getZahtjevId(),
                korisnickiUpitnik.getTezinaDivljaci(),
                korisnickiUpitnik.getDaljinaMete(),
                korisnickiUpitnik.getSkitIzabran(),
                korisnickiUpitnik.getTrapIzabran(),
                korisnickiUpitnik.getNosenjeIzabrano(),
                korisnickiUpitnik.getPosjedovanjeIzabrano()
        );

        FactHandle konkretnaNamjenaFazaFactHandle = kieSession.insert(konkretnaNamjenaFaza);

        kieSession.getAgenda().getAgendaGroup("Konkretna namjena").setFocus();
        kieSession.fireAllRules();
        kieSession.delete(konkretnaNamjenaFazaFactHandle);

        List<Kalibar> sviKalibri = this.kalibarRepository.findAll();
        sviKalibri.forEach(kieSession::insert);

        kieSession.getAgenda().getAgendaGroup("Odredjivanje dozvoljenih kalibara").setFocus();
        kieSession.fireAllRules();

        //ovo ostaje
        List<Kalibar> zeljeniKalibri = this.kalibarRepository.findAllById(korisnickiUpitnik.getKalibri());

        List<Oruzje> oruzja = this.oruzjeRepository.findAll();
        oruzja.forEach(kieSession::insert);

        LicnePreferenceFaza licnePreferenceFaza = new LicnePreferenceFaza(korisnickiUpitnik.getZahtjevId(), korisnickiUpitnik.getMehanizmiHranjenja(), korisnickiUpitnik.getMehanizmiOkidanja(), zeljeniKalibri);
        FactHandle licnePreferenceFactHandle = kieSession.insert(licnePreferenceFaza);
        kieSession.getAgenda().getAgendaGroup("Preporuka oruzja").setFocus();
        kieSession.fireAllRules();

        kieSession.delete(licnePreferenceFactHandle);

        Collection<PreporucenoOruzjeFact> myFacts = (Collection<PreporucenoOruzjeFact>) kieSession.getObjects(new ClassObjectFilter(PreporucenoOruzjeFact.class));

        PreporucenoOruzjeFact preporucenoOruzjeFact = myFacts.stream().filter(fact -> fact.getZahtjevId().equals(korisnickiUpitnik.getZahtjevId())).collect(Collectors.toList()).get(0);

        kieSession.dispose();

        return preporucenoOruzjeFact;
    }
}
