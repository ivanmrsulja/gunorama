package rs.sbnz.gunorama.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KonkretnaNamjenaFact {

    private KonkretnaNamjena konkretnaNamjena;

    private Integer zahtjevId;


}
