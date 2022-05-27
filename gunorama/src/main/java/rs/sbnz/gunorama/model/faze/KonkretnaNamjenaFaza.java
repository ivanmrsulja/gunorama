package rs.sbnz.gunorama.model.faze;


import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class KonkretnaNamjenaFaza extends Faza {

    private Double tezinaDivljaci;

    private Double daljinaMete;

    private Boolean skitIzabran;

    private Boolean trapIzabran;

    private Boolean nosenjeIzabrano;

    private Boolean posjedovanjeIzabrano;

    private List<KonkretnaNamjena> konkretneNamjene =  Arrays.asList(
            KonkretnaNamjena.LOV_SITNE_DIVLJACI,
            KonkretnaNamjena.LOV_KRUPNE_DIVLJACI,
            KonkretnaNamjena.SKIT,
            KonkretnaNamjena.TRAP,
            KonkretnaNamjena.KRATKOMETNO_GADJANJE,
            KonkretnaNamjena.DALEKOMETNO_GADJANJE,
            KonkretnaNamjena.NOSENJE,
            KonkretnaNamjena.POSJEDOVANJE
    );


    public KonkretnaNamjenaFaza(Integer zahtjevId, Double tezinaDivljaci, Double daljinaMete, Boolean skitIzabran, Boolean trapIzabran, Boolean nosenjeIzabrano, Boolean posjedovanjeIzabrano) {
        super(zahtjevId);
        this.tezinaDivljaci = tezinaDivljaci;
        this.daljinaMete = daljinaMete;
        this.skitIzabran = skitIzabran;
        this.trapIzabran = trapIzabran;
        this.nosenjeIzabrano = nosenjeIzabrano;
        this.posjedovanjeIzabrano = posjedovanjeIzabrano;
    }

}
