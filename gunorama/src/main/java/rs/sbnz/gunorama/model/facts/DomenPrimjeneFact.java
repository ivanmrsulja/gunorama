package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;

@Getter
@Setter
public class DomenPrimjeneFact {

    private DomenPrimjene domenPrimjene;

    private Integer zahtjevId;


    public DomenPrimjeneFact() {
    }

    public DomenPrimjeneFact(DomenPrimjene domenPrimjene, Integer zahtjevId) {
        this.domenPrimjene = domenPrimjene;
        this.zahtjevId = zahtjevId;
    }
}
