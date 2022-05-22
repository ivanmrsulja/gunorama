package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Faza {

    protected Integer zahtjevId;

    protected Faza(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }
}
