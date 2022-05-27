package rs.sbnz.gunorama.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradjaninZdravstvenoSposobanFact {

    private Integer zahtjevId;

    private boolean sposoban;

}
