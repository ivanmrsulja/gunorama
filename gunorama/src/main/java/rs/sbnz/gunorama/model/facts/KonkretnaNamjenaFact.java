package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;


@Getter
@Setter
public class KonkretnaNamjenaFact extends Fact{

    private KonkretnaNamjena konkretnaNamjena;


    public KonkretnaNamjenaFact(Integer zahtjevId, KonkretnaNamjena konkretnaNamjena) {
        super(zahtjevId);
        this.konkretnaNamjena = konkretnaNamjena;
    }
}
