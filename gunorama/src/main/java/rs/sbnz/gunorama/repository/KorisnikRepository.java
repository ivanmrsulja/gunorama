package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.sbnz.gunorama.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
}
