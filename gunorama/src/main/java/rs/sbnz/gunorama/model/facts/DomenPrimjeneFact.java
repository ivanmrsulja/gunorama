package rs.sbnz.gunorama.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomenPrimjeneFact {

    private Integer zahtjevId;

    private DomenPrimjene domenPrimjene;

}
