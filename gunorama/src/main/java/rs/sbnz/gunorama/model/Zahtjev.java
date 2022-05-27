package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "zahtjevi")
public class Zahtjev extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik;

    @Column(name = "odobren", nullable = false)
    private boolean odobren = false;

    @Column(name = "razlog_odbijanja", nullable = true)
    private String razlogOdbijanja = null;

    @Column(name = "domen_primjene", nullable = true)
    private DomenPrimjene domenPrimjene = null;

    @Column(name = "konkretna_namjena", nullable = true)
    private KonkretnaNamjena konkretnaNamjena = null;

    @ElementCollection(targetClass = TipDokumenta.class)
    @Column(name = "dokumenti", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<TipDokumenta> dokumenti = new ArrayList<>();

    public Zahtjev() {}

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
}
