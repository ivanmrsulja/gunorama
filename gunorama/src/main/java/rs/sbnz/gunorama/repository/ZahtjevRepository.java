package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.gunorama.model.Zahtjev;

public interface ZahtjevRepository extends JpaRepository<Zahtjev, Integer> {
}
