package rs.sbnz.gunorama.model;

import rs.sbnz.gunorama.model.enums.Dokument;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import java.util.ArrayList;
import java.util.List;

public class Zahtjev extends BaseEntity {

    private Korisnik korisnik;

    private boolean odobren = false;

    private String razlogOdbijanja = null;

    private DomenPrimjene domenPrimjene = null;

    private KonkretnaNamjena konkretnaNamjena = null;

    private List<Dokument> dokumenti = new ArrayList<>();




    public Zahtjev() {
    }

    public Zahtjev(Korisnik korisnik, boolean odobren, String razlogOdbijanja) {
        this.korisnik = korisnik;
        this.odobren = odobren;
        this.razlogOdbijanja = razlogOdbijanja;
    }

    public Zahtjev(Integer id, Korisnik korisnik, boolean odobren, String razlogOdbijanja) {
        super(id);
        this.korisnik = korisnik;
        this.odobren = odobren;
        this.razlogOdbijanja = razlogOdbijanja;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public String getRazlogOdbijanja() {
        return razlogOdbijanja;
    }

    public void setRazlogOdbijanja(String razlogOdbijanja) {
        this.razlogOdbijanja = razlogOdbijanja;
    }


    public DomenPrimjene getDomenPrimjene() {
        return domenPrimjene;
    }

    public void setDomenPrimjene(DomenPrimjene domenPrimjene) {
        this.domenPrimjene = domenPrimjene;
    }

    public KonkretnaNamjena getKonkretnaNamjena() {
        return konkretnaNamjena;
    }

    public void setKonkretnaNamjena(KonkretnaNamjena konkretnaNamjena) {
        this.konkretnaNamjena = konkretnaNamjena;
    }

    public List<Dokument> getDokumenti() {
        return dokumenti;
    }

    public void setDokumenti(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }
}
