package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Korisnik extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Korisnik() {}

    public Korisnik(Integer id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }
}
