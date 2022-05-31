package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Role(Role.Type.EVENT)
@Table(name = "zahtjevi")
public class Zahtjev extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik;

    @Column(name = "odobren", nullable = false)
    private boolean odobren = false;

    @Column(name = "razlog_odbijanja", nullable = true)
    private String razlogOdbijanja = null;

    @Column(name = "nepravilnost", nullable = true)
    private String nepravilnost = null;

    @Column(name = "domen_primjene", nullable = true)
    private DomenPrimjene domenPrimjene = null;

    @Column(name = "konkretna_namjena", nullable = true)
    private KonkretnaNamjena konkretnaNamjena = null;

    @ElementCollection(targetClass = TipDokumenta.class, fetch = FetchType.EAGER)
    @Column(name = "dokumenti", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<TipDokumenta> dokumenti = new ArrayList<>();

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


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;
        Zahtjev zahtjev = (Zahtjev) o;
        return this.getId().equals(zahtjev.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
