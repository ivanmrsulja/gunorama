package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DozvoljeniKalibriFact extends Fact{

    Set<Kalibar> dozvoljeniKalibri;

    public DozvoljeniKalibriFact(Integer zahtjevId, Set<Kalibar> dozvoljeniKalibri) {
        super(zahtjevId);
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }


    public DozvoljeniKalibriFact(Integer zahtjevId) {
        super(zahtjevId);
        this.dozvoljeniKalibri = new HashSet<>();
    }

    public DozvoljeniKalibriFact dodajKalibar(Kalibar kalibar){
        this.dozvoljeniKalibri.add(kalibar);
        return this;
    }
}
