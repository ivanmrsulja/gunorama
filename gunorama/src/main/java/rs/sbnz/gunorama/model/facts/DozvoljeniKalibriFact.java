package rs.sbnz.gunorama.model.facts;

import rs.sbnz.gunorama.model.Kalibar;

import java.util.List;

public class DozvoljeniKalibriFact {

    List<Kalibar> dozvoljeniKalibri;


    public DozvoljeniKalibriFact() {
    }

    public DozvoljeniKalibriFact(List<Kalibar> dozvoljeniKalibri) {
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }

    public List<Kalibar> getDozvoljeniKalibri() {
        return dozvoljeniKalibri;
    }

    public void setDozvoljeniKalibri(List<Kalibar> dozvoljeniKalibri) {
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }
}
