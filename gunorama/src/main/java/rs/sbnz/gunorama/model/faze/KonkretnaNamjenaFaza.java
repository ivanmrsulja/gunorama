package rs.sbnz.gunorama.model.faze;


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


    public Double getTezinaDivljaci() {
        return tezinaDivljaci;
    }

    public void setTezinaDivljaci(Double tezinaDivljaci) {
        this.tezinaDivljaci = tezinaDivljaci;
    }

    public Double getDaljinaMete() {
        return daljinaMete;
    }

    public void setDaljinaMete(Double daljinaMete) {
        this.daljinaMete = daljinaMete;
    }

    public Boolean getSkitIzabran() {
        return skitIzabran;
    }

    public void setSkitIzabran(Boolean skitIzabran) {
        this.skitIzabran = skitIzabran;
    }

    public Boolean getTrapIzabran() {
        return trapIzabran;
    }

    public void setTrapIzabran(Boolean trapIzabran) {
        this.trapIzabran = trapIzabran;
    }

    public Boolean getNosenjeIzabrano() {
        return nosenjeIzabrano;
    }

    public void setNosenjeIzabrano(Boolean nosenjeIzabrano) {
        this.nosenjeIzabrano = nosenjeIzabrano;
    }

    public Boolean getPosjedovanjeIzabrano() {
        return posjedovanjeIzabrano;
    }

    public void setPosjedovanjeIzabrano(Boolean posjedovanjeIzabrano) {
        this.posjedovanjeIzabrano = posjedovanjeIzabrano;
    }
}
