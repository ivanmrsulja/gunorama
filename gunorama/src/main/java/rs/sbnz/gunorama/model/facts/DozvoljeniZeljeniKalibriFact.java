package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DozvoljeniZeljeniKalibriFact extends Fact{

    Set<Kalibar> dozvoljeniKalibri;

    public DozvoljeniZeljeniKalibriFact(Integer zahtjevId, Set<Kalibar> dozvoljeniKalibri) {
        super(zahtjevId);
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }


    public DozvoljeniZeljeniKalibriFact(Integer zahtjevId) {
        super(zahtjevId);
        this.dozvoljeniKalibri = new HashSet<>();
    }

    public DozvoljeniZeljeniKalibriFact dodajKalibar(Kalibar kalibar){
        this.dozvoljeniKalibri.add(kalibar);
        return this;
    }
}
