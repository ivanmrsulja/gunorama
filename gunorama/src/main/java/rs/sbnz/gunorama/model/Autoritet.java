package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "autoritet")
@Getter
@Setter
public class Autoritet extends BaseEntity implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "autoritet_dozvole", joinColumns = @JoinColumn(name = "autoritet_id"), inverseJoinColumns = @JoinColumn(name = "dozvola_id"))
    private Set<Dozvola> dozvole = new HashSet<>();

    public Autoritet(String name) {
        this.name = name;
    }

    public Autoritet() {

    }

    @Override
    public String getAuthority() {
        return this.name;
    }


    public Autoritet dodajDozvolu(Dozvola dozvola) {
        this.dozvole.add(dozvola);
        return this;
    }
}
