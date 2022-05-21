package rs.sbnz.gunorama.model;

import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import java.util.List;

public class Kalibar {

    private String naziv;

    private List<KonkretnaNamjena> namjene;

    public Kalibar() {
    }

    public Kalibar(String naziv, List<KonkretnaNamjena> namjene) {
        this.naziv = naziv;
        this.namjene = namjene;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<KonkretnaNamjena> getNamjene() {
        return namjene;
    }

    public void setNamjene(List<KonkretnaNamjena> namjene) {
        this.namjene = namjene;
    }
}
