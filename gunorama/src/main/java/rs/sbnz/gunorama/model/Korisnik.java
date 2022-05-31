package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "korisnici")
public class Korisnik extends BaseEntity implements UserDetails {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "jmbg", nullable = false)
    private String jmbg;



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "autoritet_id")
    private Autoritet autoritet;

    public Korisnik() {
    }


    public Korisnik(String email, String password, String jmbg) {
        super();
        this.email = email;
        this.password = password;
        this.jmbg = jmbg;
    }

    public Korisnik(String email, String password, String jmbg, Autoritet autoritet) {
        this.email = email;
        this.password = password;
        this.jmbg = jmbg;
        this.autoritet = autoritet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autoritet.getDozvole();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
