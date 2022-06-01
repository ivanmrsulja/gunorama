package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.gunorama.model.Autoritet;

public interface AutoritetRepository extends JpaRepository<Autoritet, Integer> {

    Autoritet findAutoritetByName(String name);
}
