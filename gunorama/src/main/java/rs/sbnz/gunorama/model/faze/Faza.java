package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Faza {

    protected Integer zahtjevId;

    public Faza() {}

    protected Faza(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }
}
