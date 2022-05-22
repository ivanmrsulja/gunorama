package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "calibers")
public class Kalibar extends BaseEntity{

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @ElementCollection(targetClass = KonkretnaNamjena.class)
    @Column(name = "namjene", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<KonkretnaNamjena> namjene;

    public Kalibar() {
    }

    public Kalibar(String naziv, List<KonkretnaNamjena> namjene) {
        this.naziv = naziv;
        this.namjene = namjene;
    }
}
