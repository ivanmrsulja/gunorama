package rs.sbnz.gunorama.model.enums;

import java.util.Arrays;
import java.util.List;


public enum DomenPrimjene {
    LOV(KonkretnaNamjena.LOV_KRUPNE_DIVLJACI, KonkretnaNamjena.LOV_SITNE_DIVLJACI),
    STRELJASTVO(KonkretnaNamjena.KRATKOMETNO_GADJANJE, KonkretnaNamjena.DALEKOMETNO_GADJANJE),
    LOVNO_STRELJASTVO(KonkretnaNamjena.SKIT, KonkretnaNamjena.TRAP),
    SAMOODBRANA(KonkretnaNamjena.POSJEDOVANJE, KonkretnaNamjena.NOSENJE);


    private final List<KonkretnaNamjena> dozvoljeneKonkretneNamjene;


    DomenPrimjene(KonkretnaNamjena... konkretneNamjene) {
        this.dozvoljeneKonkretneNamjene = Arrays.asList(konkretneNamjene);
    }


    public List<KonkretnaNamjena> getDozvoljeneKonkretneNamjene() {
        return dozvoljeneKonkretneNamjene;
    }
}
