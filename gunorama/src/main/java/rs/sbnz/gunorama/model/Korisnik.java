package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "korisnici")
public class Korisnik extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "jmbg", nullable = false)
    private String jmbg;

    @Column(name = "prestupnik", nullable = false)
    private boolean prestupnik = false;

    public Korisnik() {}

    public Korisnik(String email, String password, String jmbg) {
        super();
        this.email = email;
        this.password = password;
        this.jmbg = jmbg;
    }
}
