package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradjaninZdravstvenoSposobanFact extends Fact{

    private boolean sposoban;

    public GradjaninZdravstvenoSposobanFact(Integer zahtjevId, boolean sposoban) {
        super(zahtjevId);
        this.sposoban = sposoban;
    }
}
