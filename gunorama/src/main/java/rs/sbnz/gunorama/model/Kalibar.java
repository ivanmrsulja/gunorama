package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "kalibri")
public class Kalibar extends BaseEntity{

    @Column(name = "naziv", nullable = false, unique = true)
    private String naziv;

    @ElementCollection(targetClass = KonkretnaNamjena.class, fetch = FetchType.EAGER)
    @Column(name = "namjene", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<KonkretnaNamjena> namjene;



    @Column(name = "u_milimetrima")
    private Double uMilimetrima;

    public Kalibar() {
    }

    public Kalibar(String naziv, List<KonkretnaNamjena> namjene, Double uMilimetrima) {
        this.naziv = naziv;
        this.namjene = namjene;
        this.uMilimetrima = uMilimetrima;
    }

    public Kalibar(String naziv, List<KonkretnaNamjena> namjene) {
        this.naziv = naziv;
        this.namjene = namjene;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kalibar kalibar = (Kalibar) o;
        return Objects.equals(naziv, kalibar.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    @Override
    public String toString() {
        return "Kalibar{" +
                "naziv='" + naziv + '\'' +
                '}';
    }
}
