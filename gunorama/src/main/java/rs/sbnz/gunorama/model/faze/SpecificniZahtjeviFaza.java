package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SpecificniZahtjeviFaza extends Faza{

    private List<TipDokumenta> dokumenti = new ArrayList<>();


    public SpecificniZahtjeviFaza(List<TipDokumenta> dokumenti, Integer zahtjevId) {
        super(zahtjevId);
        this.dokumenti = dokumenti;
    }
}
