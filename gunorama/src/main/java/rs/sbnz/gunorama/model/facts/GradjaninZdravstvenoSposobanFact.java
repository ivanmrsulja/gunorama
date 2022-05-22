package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradjaninZdravstvenoSposobanFact {

    private Integer zahtjevId;

    private boolean sposoban;

    public GradjaninZdravstvenoSposobanFact() {}

    public GradjaninZdravstvenoSposobanFact(Integer zahtjevId, boolean sposoban) {
        this.zahtjevId = zahtjevId;
        this.sposoban = sposoban;
    }
}
