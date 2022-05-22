package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;

import java.util.List;

@Getter
@Setter
public class DozvoljeniKalibriFact {

    List<Kalibar> dozvoljeniKalibri;

    public DozvoljeniKalibriFact() {}

    public DozvoljeniKalibriFact(List<Kalibar> dozvoljeniKalibri) {
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }
}
