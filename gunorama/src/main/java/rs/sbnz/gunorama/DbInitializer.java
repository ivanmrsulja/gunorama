package rs.sbnz.gunorama;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;
import rs.sbnz.gunorama.repository.KalibarRepository;
import rs.sbnz.gunorama.repository.OruzjeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DbInitializer implements ApplicationRunner {

    private final KalibarRepository kalibarRepository;

    private final OruzjeRepository oruzjeRepository;

    public DbInitializer(KalibarRepository kalibarRepository, OruzjeRepository oruzjeRepository) {
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Kalibar k1 = new Kalibar("9MM_PARABELLUM", new ArrayList<>(Arrays.asList(KonkretnaNamjena.POSJEDOVANJE, KonkretnaNamjena.NOSENJE)));
        Kalibar k2 = new Kalibar("12GA_BUCKSHOT", new ArrayList<>(Arrays.asList(KonkretnaNamjena.POSJEDOVANJE, KonkretnaNamjena.LOV_SITNE_DIVLJACI, KonkretnaNamjena.SKIT)));
        Kalibar k3 = new Kalibar(".308_WINCHESTER", new ArrayList<>(Arrays.asList(KonkretnaNamjena.LOV_KRUPNE_DIVLJACI)));
        Kalibar k4 = new Kalibar("12GA_SLUG", new ArrayList<>(Arrays.asList(KonkretnaNamjena.LOV_KRUPNE_DIVLJACI)));
        Kalibar k5 = new Kalibar(".22_LR", new ArrayList<>(Arrays.asList(KonkretnaNamjena.KRATKOMETNO_GADJANJE)));
        Kalibar k6 = new Kalibar(".22 WMRF", new ArrayList<>(Arrays.asList(KonkretnaNamjena.DALEKOMETNO_GADJANJE)));
        Kalibar k7 = new Kalibar("16GA_BUCKSHOT", new ArrayList<>(Arrays.asList(KonkretnaNamjena.POSJEDOVANJE, KonkretnaNamjena.LOV_SITNE_DIVLJACI, KonkretnaNamjena.TRAP)));

        kalibarRepository.save(k1);
        kalibarRepository.save(k2);
        kalibarRepository.save(k3);
        kalibarRepository.save(k4);
        kalibarRepository.save(k5);
        kalibarRepository.save(k6);
        kalibarRepository.save(k7);

        Oruzje o1 = new Oruzje("Beretta M9", new HashSet<>(Arrays.asList(k1)), MehanizamHranjenja.POLUAUTOMATSKI, MehanizamOkidanja.OTVORENI);
        Oruzje o2 = new Oruzje("Zastava MP22", new HashSet<>(Arrays.asList(k5, k6)), MehanizamHranjenja.OBRTNOCEPNI, MehanizamOkidanja.ZATVORENI);
        Oruzje o3 = new Oruzje("Benelli M4", new HashSet<>(Arrays.asList(k2, k4)), MehanizamHranjenja.POLUAUTOMATSKI, MehanizamOkidanja.OTVORENI);
        Oruzje o4 = new Oruzje("TOZ 34", new HashSet<>(Arrays.asList(k2, k4)), MehanizamHranjenja.PREKLAPAJUCI, MehanizamOkidanja.ZATVORENI);
        Oruzje o5 = new Oruzje("TOZ 63", new HashSet<>(Arrays.asList(k7)), MehanizamHranjenja.PREKLAPAJUCI, MehanizamOkidanja.OTVORENI);
        Oruzje o6 = new Oruzje("Zastava LK M70", new HashSet<>(Arrays.asList(k3)), MehanizamHranjenja.OBRTNOCEPNI, MehanizamOkidanja.ZATVORENI);

        oruzjeRepository.save(o1);
        oruzjeRepository.save(o2);
        oruzjeRepository.save(o3);
        oruzjeRepository.save(o4);
        oruzjeRepository.save(o5);
        oruzjeRepository.save(o6);
    }
}
