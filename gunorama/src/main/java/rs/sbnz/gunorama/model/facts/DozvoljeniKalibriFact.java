package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;

import java.util.List;

@Getter
@Setter
public class DozvoljeniKalibriFact extends Fact{

    List<Kalibar> dozvoljeniKalibri;

    public DozvoljeniKalibriFact(Integer zahtjevId, List<Kalibar> dozvoljeniKalibri) {
        super(zahtjevId);
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }
}
