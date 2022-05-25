package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Dokument;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DomenPrimjeneFaza extends Faza{

    private List<Dokument> dokumenti = new ArrayList<>();

    public DomenPrimjeneFaza(List<Dokument> dokumenti, Integer zahtjevId) {
        super(zahtjevId);
        this.dokumenti = dokumenti;
    }
}
