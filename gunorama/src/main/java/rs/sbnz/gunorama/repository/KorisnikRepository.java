package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.sbnz.gunorama.model.Korisnik;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

    @Query("select k from Korisnik k where k.jmbg = :jmbg")
    Optional<Korisnik> findOneByJmbg(String jmbg);

    Optional<Korisnik> findByEmail(String email);
}
