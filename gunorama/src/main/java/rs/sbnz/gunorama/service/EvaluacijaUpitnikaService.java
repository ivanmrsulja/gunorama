package rs.sbnz.gunorama.service;


import lombok.extern.slf4j.Slf4j;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.dto.KorisnickiUpitnik;
import rs.sbnz.gunorama.exception.NotFoundException;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.facts.DozvoljeniKalibriFact;
import rs.sbnz.gunorama.model.facts.KonkretnaNamjenaFact;
import rs.sbnz.gunorama.model.faze.KonkretnaNamjenaFaza;
import rs.sbnz.gunorama.model.faze.LicnePreferenceFaza;
import rs.sbnz.gunorama.repository.KalibarRepository;
import rs.sbnz.gunorama.repository.OruzjeRepository;
import rs.sbnz.gunorama.repository.ZahtjevRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvaluacijaUpitnikaService {

    private final KieSession kieSession;

    private final ZahtjevRepository zahtjevRepository;

    private final KalibarRepository kalibarRepository;

    private final OruzjeRepository oruzjeRepository;

    @Autowired
    public EvaluacijaUpitnikaService(KieSession kieSession, ZahtjevRepository zahtjevRepository, KalibarRepository kalibarRepository, OruzjeRepository oruzjeRepository) {
        this.kieSession = kieSession;
        this.zahtjevRepository = zahtjevRepository;
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
    }


    public KonkretnaNamjenaFact evaluate(KorisnickiUpitnik korisnickiUpitnik) {

        Zahtjev zahtjev = this.zahtjevRepository.findById(korisnickiUpitnik.getZahtjevId())
                .orElseThrow(() -> new NotFoundException(String.format("Zahtjev sa id-ijem: %d nije pronadjen.", korisnickiUpitnik.getZahtjevId())));

        KonkretnaNamjenaFaza konkretnaNamjenaFaza = new KonkretnaNamjenaFaza(
                zahtjev.getId(),
                korisnickiUpitnik.getTezinaDivljaci(),
                korisnickiUpitnik.getDaljinaMete(),
                korisnickiUpitnik.getSkitIzabran(),
                korisnickiUpitnik.getTrapIzabran(),
                korisnickiUpitnik.getNosenjeIzabrano(),
                korisnickiUpitnik.getPosjedovanjeIzabrano()
        );

        kieSession.insert(zahtjev);

        FactHandle konkretnaNamjenaFactHandle = kieSession.insert(konkretnaNamjenaFaza);
        kieSession.getAgenda().getAgendaGroup("Konkretna namjena").setFocus();
        kieSession.fireAllRules();
        kieSession.delete(konkretnaNamjenaFactHandle);

        //TODO: ovdje dodaj da se aktivira template pravilo, da se nadju dozvoljeni kalibri za datu namjenu i generise ovaj fact. Sad to radim rucno


        List<Kalibar> dozvoljeniKalibri = this.kalibarRepository.findAll()
                .stream().filter(kalibar -> kalibar.getNamjene().contains(zahtjev.getKonkretnaNamjena()))
                .collect(Collectors.toList());

        DozvoljeniKalibriFact dozvoljeniKalibriFact = new DozvoljeniKalibriFact(zahtjev.getId());
        dozvoljeniKalibri.forEach(dozvoljeniKalibriFact::dodajKalibar);
//        dozvoljeniKalibriFact.getDozvoljeniKalibri().forEach(dozvoljeniKalibar -> log.info(dozvoljeniKalibar.getNaziv()));
        log.info("==DOZVOLJENI KALIBRI");
        log.info(dozvoljeniKalibriFact.getDozvoljeniKalibri().toString());

        //ovo ostaje
        List<Kalibar> zeljeniKalibri = this.kalibarRepository.findAllById(korisnickiUpitnik.getKalibri());


        List<Oruzje> oruzja = this.oruzjeRepository.findAll();
        oruzja.forEach(kieSession::insert);


//        for(Oruzje o: oruzja){
//            log.info("==========");
//            log.info(o.getNaziv());
//            log.info("HRANJENJE: " + o.getMehanizamHranjenja());
//            log.info("OKIDANJE: " + o.getMehanizamOkidanja());
//            log.info("KALIBRI: " + o.getDozvoljeniKalibri());
//            log.info("PRESJEK SA DOZVOLJENIM KALIBRIMA: " + Sets.intersection(o.getDozvoljeniKalibri(), dozvoljeniKalibriFact.getDozvoljeniKalibri()));
//        }


        LicnePreferenceFaza licnePreferenceFaza = new LicnePreferenceFaza(zahtjev.getId(), korisnickiUpitnik.getMehanizmiHranjenja(), korisnickiUpitnik.getMehanizmiOkidanja(), zeljeniKalibri);
        FactHandle licnePreferenceFactHandle = kieSession.insert(licnePreferenceFaza);
        kieSession.insert(dozvoljeniKalibriFact);
        kieSession.getAgenda().getAgendaGroup("Preporuka oruzja").setFocus();
        kieSession.fireAllRules();
        kieSession.delete(licnePreferenceFactHandle);


        Collection<KonkretnaNamjenaFact> myFacts = (Collection<KonkretnaNamjenaFact>) kieSession.getObjects(new ClassObjectFilter(KonkretnaNamjenaFact.class));

        return myFacts.stream().filter(fact -> fact.getZahtjevId().equals(zahtjev.getId())).collect(Collectors.toList()).get(0);
    }
}
