package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;

@Getter
@Setter
public class DomenPrimjeneFact extends Fact{


    private DomenPrimjene domenPrimjene;

    public DomenPrimjeneFact(Integer zahtjevId, DomenPrimjene domenPrimjene) {
        super(zahtjevId);
        this.domenPrimjene = domenPrimjene;
    }
}
