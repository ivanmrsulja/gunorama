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
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.facts.DozvoljeniKalibriFact;
import rs.sbnz.gunorama.model.facts.PreporucenoOruzjeFact;
import rs.sbnz.gunorama.model.faze.KonkretnaNamjenaFaza;
import rs.sbnz.gunorama.model.faze.LicnePreferenceFaza;
import rs.sbnz.gunorama.model.templates.OdredjivanjeDozvoljenihKalibaraTemplateModel;
import rs.sbnz.gunorama.repository.KalibarRepository;
import rs.sbnz.gunorama.repository.OruzjeRepository;
import rs.sbnz.gunorama.repository.ZahtjevRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvaluacijaUpitnikaService {

    private final KieSession kieSession;

    private final KalibarRepository kalibarRepository;

    private final OruzjeRepository oruzjeRepository;

    @Autowired
    public EvaluacijaUpitnikaService(KieSession kieSession, KalibarRepository kalibarRepository, OruzjeRepository oruzjeRepository) {
        this.kieSession = kieSession;
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
    }


    public PreporucenoOruzjeFact evaluate(KorisnickiUpitnik korisnickiUpitnik) {

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

        //TODO: ovdje dodaj da se aktivira template pravilo, da se nadju dozvoljeni kalibri za datu namjenu i generise ovaj fact. Sad to radim rucno
        //TODO: za sad ostavi da su svi kalibri dozvoljeni

        List<OdredjivanjeDozvoljenihKalibaraTemplateModel> templateModels = new ArrayList<>();
        for(KonkretnaNamjena konkretnaNamjena: KonkretnaNamjena.values()){
            templateModels.add(new OdredjivanjeDozvoljenihKalibaraTemplateModel(konkretnaNamjena, korisnickiUpitnik.getMinimalniPrecnikUMilimetrima(), korisnickiUpitnik.getMaksimalniPrecnikUMilimetrima()));
        }

//        List<Kalibar> dozvoljeniKalibri = this.kalibarRepository.findAll();
//
//        DozvoljeniKalibriFact dozvoljeniKalibriFact = new DozvoljeniKalibriFact(korisnickiUpitnik.getZahtjevId());
//        dozvoljeniKalibri.forEach(dozvoljeniKalibriFact::dodajKalibar);

        //ovo ostaje
        List<Kalibar> zeljeniKalibri = this.kalibarRepository.findAllById(korisnickiUpitnik.getKalibri());


        List<Oruzje> oruzja = this.oruzjeRepository.findAll();
        List<FactHandle> oruzjaFactHandles = new ArrayList<>();
        for (Oruzje oruzje : oruzja) {
            FactHandle factHandle = kieSession.insert(oruzje);
            oruzjaFactHandles.add(factHandle);
        }


        LicnePreferenceFaza licnePreferenceFaza = new LicnePreferenceFaza(korisnickiUpitnik.getZahtjevId(), korisnickiUpitnik.getMehanizmiHranjenja(), korisnickiUpitnik.getMehanizmiOkidanja(), zeljeniKalibri);
        FactHandle licnePreferenceFactHandle = kieSession.insert(licnePreferenceFaza);
        //FactHandle dozvoljeniKalibriFactHandle = kieSession.insert(dozvoljeniKalibriFact);
        kieSession.getAgenda().getAgendaGroup("Preporuka oruzja").setFocus();
        kieSession.fireAllRules();

        kieSession.delete(licnePreferenceFactHandle);
        //kieSession.delete(dozvoljeniKalibriFactHandle);
        oruzjaFactHandles.forEach(kieSession::delete);


        Collection<PreporucenoOruzjeFact> myFacts = (Collection<PreporucenoOruzjeFact>) kieSession.getObjects(new ClassObjectFilter(PreporucenoOruzjeFact.class));

        PreporucenoOruzjeFact preporucenoOruzjeFact = myFacts.stream().filter(fact -> fact.getZahtjevId().equals(korisnickiUpitnik.getZahtjevId())).collect(Collectors.toList()).get(0);

        List<FactHandle> toDelete = kieSession.getFactHandles().stream().filter(factHandle ->
                factHandle.toExternalForm().contains("KonkretnaNamjenaFact") ||
                factHandle.toExternalForm().contains("PreporucenoOruzjeFact")
        ).collect(Collectors.toList());
        toDelete.forEach(kieSession::delete);

        return preporucenoOruzjeFact;
    }
}
