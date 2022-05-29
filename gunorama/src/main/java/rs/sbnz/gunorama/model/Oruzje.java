package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "oruzje")
public class Oruzje extends BaseEntity {

    @Column(name = "naziv", nullable = false, unique = true)
    private String naziv;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Kalibar> dozvoljeniKalibri = new HashSet<>();

    @Column(name = "mehanizam_hranjenja", nullable = false)
    private MehanizamHranjenja mehanizamHranjenja;

    @Column(name = "mehanizam_okidanja", nullable = false)
    private MehanizamOkidanja mehanizamOkidanja;


    public Oruzje() {
    }


    public Oruzje(String naziv, Set<Kalibar> dozvoljeniKalibri, MehanizamHranjenja mehanizamHranjenja, MehanizamOkidanja mehanizamOkidanja) {
        this.naziv = naziv;
        this.dozvoljeniKalibri = dozvoljeniKalibri;
        this.mehanizamHranjenja = mehanizamHranjenja;
        this.mehanizamOkidanja = mehanizamOkidanja;
    }

    public Oruzje(Integer id, String naziv, Set<Kalibar> dozvoljeniKalibri, MehanizamHranjenja mehanizamHranjenja, MehanizamOkidanja mehanizamOkidanja) {
        super(id);
        this.naziv = naziv;
        this.dozvoljeniKalibri = dozvoljeniKalibri;
        this.mehanizamHranjenja = mehanizamHranjenja;
        this.mehanizamOkidanja = mehanizamOkidanja;
    }

    @Override
    public String toString() {
        return "Oruzje{" +
                "naziv='" + naziv + '\'' +
                ", mehanizamHranjenja=" + mehanizamHranjenja +
                ", mehanizamOkidanja=" + mehanizamOkidanja +
                '}';
    }
}
