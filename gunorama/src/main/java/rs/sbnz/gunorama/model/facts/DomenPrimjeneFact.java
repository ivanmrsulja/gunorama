package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;

@Getter
@Setter
public class DomenPrimjeneFact {

    private Integer zahtjevId;

    private DomenPrimjene domenPrimjene;

    public DomenPrimjeneFact() {
    }

    public DomenPrimjeneFact(Integer zahtjevId, DomenPrimjene domenPrimjene) {
        this.domenPrimjene = domenPrimjene;
        this.zahtjevId = zahtjevId;
    }
}
