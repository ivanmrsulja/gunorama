package rs.sbnz.gunorama.model.facts;

import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

public class KonkretnaNamjenaFact {

    private KonkretnaNamjena konkretnaNamjena;

    private Integer zahtjevId;

    public KonkretnaNamjenaFact(KonkretnaNamjena konkretnaNamjena, Integer zahtjevId) {
        this.konkretnaNamjena = konkretnaNamjena;
        this.zahtjevId = zahtjevId;
    }

    public KonkretnaNamjenaFact() {
    }

    public KonkretnaNamjena getKonkretnaNamjena() {
        return konkretnaNamjena;
    }

    public void setKonkretnaNamjena(KonkretnaNamjena konkretnaNamjena) {
        this.konkretnaNamjena = konkretnaNamjena;
    }

    public Integer getZahtjevId() {
        return zahtjevId;
    }

    public void setZahtjevId(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }
}
