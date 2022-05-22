package rs.sbnz.gunorama.model.faze;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KonkretnaNamjenaFaza extends Faza {

    private Double tezinaDivljaci;

    private Double daljinaMete;

    private Boolean skitIzabran;

    private Boolean trapIzabran;

    private Boolean nosenjeIzabrano;

    private Boolean posjedovanjeIzabrano;


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
