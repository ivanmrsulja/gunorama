package rs.sbnz.gunorama;

import org.kie.api.runtime.KieSession;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.sbnz.gunorama.model.*;
import rs.sbnz.gunorama.model.enums.*;
import rs.sbnz.gunorama.repository.*;

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

    private final KorisnikRepository korisnikRepository;

    private final DozvolaRepository dozvolaRepository;

    private final AutoritetRepository autoritetRepository;

    private final KieSession kieSession;

    private final PasswordEncoder passwordEncoder;

    public DbInitializer(KalibarRepository kalibarRepository,
                         OruzjeRepository oruzjeRepository,
                         PotrebanUslovRepository potrebanUslovRepository,
                         ZahtjevRepository zahtjevRepository,
                         KorisnikRepository korisnikRepository,
                         DozvolaRepository dozvolaRepository,
                         AutoritetRepository autoritetRepository,
                         KieSession kieSession, PasswordEncoder passwordEncoder) {
        this.kalibarRepository = kalibarRepository;
        this.oruzjeRepository = oruzjeRepository;
        this.potrebanUslovRepository = potrebanUslovRepository;
        this.zahtjevRepository = zahtjevRepository;
        this.korisnikRepository = korisnikRepository;
        this.dozvolaRepository = dozvolaRepository;
        this.autoritetRepository = autoritetRepository;
        this.kieSession = kieSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        Dozvola unosenjeZahtjeva = new Dozvola("UNOSENJE_ZAHTJEVA");
        Dozvola popunjavanjeUpitnika = new Dozvola("POPUNJAVANJE_UPITNIKA");
        Dozvola dobavljanjeZahtjevaZaKorisnika = new Dozvola("DOBAVLJANJE_ZAHTJEVA_ZA_KORISNIKA");

        this.dozvolaRepository.saveAll(Arrays.asList(unosenjeZahtjeva, popunjavanjeUpitnika, dobavljanjeZahtjevaZaKorisnika));

        Autoritet policijskiSluzbenik = new Autoritet("POLICIJSKI_SLUZBENIK");
        policijskiSluzbenik.dodajDozvolu(unosenjeZahtjeva);

        Autoritet gradjanin = new Autoritet("GRADJANIN");
        gradjanin.dodajDozvolu(popunjavanjeUpitnika).dodajDozvolu(dobavljanjeZahtjevaZaKorisnika);

        this.autoritetRepository.saveAll(Arrays.asList(policijskiSluzbenik, gradjanin));



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

        Korisnik policajac = new Korisnik("mrpoliceman@email.com", passwordEncoder.encode("narodniministar"), "1015454526786", policijskiSluzbenik);
        Korisnik korisnik1 = new Korisnik("email@email.com", passwordEncoder.encode("password"), "1231231231231", gradjanin);
        Korisnik korisnik2 = new Korisnik("email2@email.com", passwordEncoder.encode("password"), "1111111111111", gradjanin);
        Korisnik korisnik3 = new Korisnik("email3@email.com", passwordEncoder.encode("password"), "2222222222222", gradjanin);
        korisnikRepository.save(korisnik1);
        korisnikRepository.save(korisnik2);
        korisnikRepository.save(korisnik3);
        korisnikRepository.save(policajac);

        Zahtjev z1 = new Zahtjev();
        z1.setOdobren(true);
        z1.setDomenPrimjene(DomenPrimjene.LOVNO_STRELJASTVO);
        z1.setKorisnik(korisnik1);

        Zahtjev zOdbijen = new Zahtjev();
        zOdbijen.setOdobren(false);
        zOdbijen.setRazlogOdbijanja("Gradjanin je podnio nepotpunu dokumentaciju.");
        zOdbijen.setDomenPrimjene(null);
        zOdbijen.setDokumenti(new ArrayList<>(Arrays.asList(TipDokumenta.DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU, TipDokumenta.DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA))); // Fali mu TipDokumenta.DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU
        zOdbijen.setKorisnik(korisnik1);

        Zahtjev z2 = new Zahtjev();
        z2.setOdobren(true);
        z2.setDomenPrimjene(DomenPrimjene.LOV);
        z2.setKorisnik(korisnik1);

        Zahtjev z3 = new Zahtjev();
        z3.setOdobren(true);
        z3.setDomenPrimjene(DomenPrimjene.STRELJASTVO);
        z3.setKorisnik(korisnik2);

        Zahtjev z4 = new Zahtjev();
        z4.setOdobren(true);
        z4.setDomenPrimjene(DomenPrimjene.SAMOODBRANA);
        z4.setKorisnik(korisnik3);

        zahtjevRepository.saveAll(Arrays.asList(z1, zOdbijen, z2, z3, z4));

        kieSession.insert(z1);
        kieSession.insert(zOdbijen);
        kieSession.insert(z2);
        kieSession.insert(z3);
        kieSession.insert(z4);
    }
}
