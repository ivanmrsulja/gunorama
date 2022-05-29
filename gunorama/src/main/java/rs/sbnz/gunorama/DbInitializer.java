package rs.sbnz.gunorama;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.PotrebanUslov;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.enums.*;
import rs.sbnz.gunorama.repository.KalibarRepository;
import rs.sbnz.gunorama.repository.OruzjeRepository;
import rs.sbnz.gunorama.repository.PotrebanUslovRepository;
import rs.sbnz.gunorama.repository.ZahtjevRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DbInitializer implements ApplicationRunner {

    private final KalibarRepository kalibarRepository;

    private final OruzjeRepository oruzjeRepository;

    private final PotrebanUslovRepository potrebanUslovRepository;

    private final ZahtjevRepository zahtjevRepository;

    public DbInitializer(KalibarRepository kalibarRepository,
                         OruzjeRepository oruzjeRepository,
                         PotrebanUslovRepository potrebanUslovRepository, ZahtjevRepository zahtjevRepository) {
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
        this.potrebanUslovRepository = potrebanUslovRepository;
        this.zahtjevRepository = zahtjevRepository;
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

        kalibarRepository.saveAll(Arrays.asList(k1, k2, k3, k4, k5, k6, k7));

        Oruzje o1 = new Oruzje("Beretta M9", new HashSet<>(Arrays.asList(k1)), MehanizamHranjenja.POLUAUTOMATSKI, MehanizamOkidanja.OTVORENI);
        Oruzje o2 = new Oruzje("Zastava MP22", new HashSet<>(Arrays.asList(k5, k6)), MehanizamHranjenja.OBRTNOCEPNI, MehanizamOkidanja.ZATVORENI);
        Oruzje o3 = new Oruzje("Benelli M4", new HashSet<>(Arrays.asList(k2, k4)), MehanizamHranjenja.POLUAUTOMATSKI, MehanizamOkidanja.OTVORENI);
        Oruzje o4 = new Oruzje("TOZ 34", new HashSet<>(Arrays.asList(k2, k4)), MehanizamHranjenja.PREKLAPAJUCI, MehanizamOkidanja.ZATVORENI);
        Oruzje o5 = new Oruzje("TOZ 63", new HashSet<>(Arrays.asList(k7)), MehanizamHranjenja.PREKLAPAJUCI, MehanizamOkidanja.OTVORENI);
        Oruzje o6 = new Oruzje("Zastava LK M70", new HashSet<>(Arrays.asList(k3)), MehanizamHranjenja.OBRTNOCEPNI, MehanizamOkidanja.ZATVORENI);

        oruzjeRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6));

        PotrebanUslov pu1 = new PotrebanUslov(DomenPrimjene.LOV, new ArrayList<>(Arrays.asList(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU, TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU, TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA)));
        PotrebanUslov pu2 = new PotrebanUslov(DomenPrimjene.LOVNO_STRELJASTVO, new ArrayList<>(Arrays.asList(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU, TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU, TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKO_STRELJACKOM_DRUSTVU, TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA)));
        PotrebanUslov pu3 = new PotrebanUslov(DomenPrimjene.STRELJASTVO, new ArrayList<>(Arrays.asList(TipDokumenta.DOKAZ_O_CLANSTVU_U_STRELJACKOM_KLUBU, TipDokumenta.DOKAZ_O_POLOZENOJ_OBUCI_ZA_RUKOVANJE_ORUZJEM, TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA)));
        PotrebanUslov pu4 = new PotrebanUslov(DomenPrimjene.SAMOODBRANA, new ArrayList<>(Arrays.asList(TipDokumenta.DOKAZ_O_UGROZENOSTI_LICNE_BEZBIJEDNOSTI, TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA)));

        potrebanUslovRepository.saveAll(Arrays.asList(pu1, pu2, pu3, pu4));

        Zahtjev z1 = new Zahtjev();
        z1.setOdobren(true);
        z1.setDomenPrimjene(DomenPrimjene.LOVNO_STRELJASTVO);

        Zahtjev z2 = new Zahtjev();
        z2.setOdobren(true);
        z2.setDomenPrimjene(DomenPrimjene.LOV);

        Zahtjev z3 = new Zahtjev();
        z3.setOdobren(true);
        z3.setDomenPrimjene(DomenPrimjene.STRELJASTVO);

        Zahtjev z4 = new Zahtjev();
        z4.setOdobren(true);
        z4.setDomenPrimjene(DomenPrimjene.SAMOODBRANA);

        zahtjevRepository.saveAll(Arrays.asList(z1, z2, z3, z4));


    }
}
