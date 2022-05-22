package rs.sbnz.gunorama.model.facts;

import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

public class KonkretnaNamjenaFact {

    private KonkretnaNamjena konkretnaNamjena;

    private Integer zahtjevId;

    public KonkretnaNamjenaFact() {}

    public KonkretnaNamjenaFact(KonkretnaNamjena konkretnaNamjena, Integer zahtjevId) {
        this.konkretnaNamjena = konkretnaNamjena;
        this.zahtjevId = zahtjevId;
    }
}
