package rs.sbnz.gunorama.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "dozvole")
@Getter
@Setter
public class Dozvola extends BaseEntity implements GrantedAuthority {


    @Column(name = "name")
    private String name;

    public Dozvola() {
    }

    public Dozvola(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
