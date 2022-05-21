package rs.sbnz.gunorama.model;

import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.HashSet;
import java.util.Set;

public class Oruzje extends BaseEntity {

    private String naziv;

    private Set<Kalibar> dozvoljeniKalibri = new HashSet<>();

    private MehanizamHranjenja mehanizamHranjenja;

    private MehanizamOkidanja mehanizamOkidanja;


    public Oruzje() {
    }

    public Oruzje(Integer id, String naziv, Set<Kalibar> dozvoljeniKalibri, MehanizamHranjenja mehanizamHranjenja, MehanizamOkidanja mehanizamOkidanja) {
        super(id);
        this.naziv = naziv;
        this.dozvoljeniKalibri = dozvoljeniKalibri;
        this.mehanizamHranjenja = mehanizamHranjenja;
        this.mehanizamOkidanja = mehanizamOkidanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Kalibar> getDozvoljeniKalibri() {
        return dozvoljeniKalibri;
    }

    public void setDozvoljeniKalibri(Set<Kalibar> dozvoljeniKalibri) {
        this.dozvoljeniKalibri = dozvoljeniKalibri;
    }

    public MehanizamHranjenja getMehanizamHranjenja() {
        return mehanizamHranjenja;
    }

    public void setMehanizamHranjenja(MehanizamHranjenja mehanizamHranjenja) {
        this.mehanizamHranjenja = mehanizamHranjenja;
    }

    public MehanizamOkidanja getMehanizamOkidanja() {
        return mehanizamOkidanja;
    }

    public void setMehanizamOkidanja(MehanizamOkidanja mehanizamOkidanja) {
        this.mehanizamOkidanja = mehanizamOkidanja;
    }
}
