package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.sbnz.gunorama.model.Zahtjev;

import java.util.List;
import java.util.Optional;

public interface ZahtjevRepository extends JpaRepository<Zahtjev, Integer> {


    @Query(value = "select z from Zahtjev z where z.odobren = true and z.korisnik.id = :korisnikId")
    List<Zahtjev> dobaviOdobreneZahtjeveZaKorisnika(@Param("korisnikId") Integer korisnikId);


    @Query(value = "select z from Zahtjev z where z.odobren = true and z.id = :zahtjevId and z.korisnik.id = :korisnikId")
    Optional<Zahtjev> dobaviOdobrenZahtjevZaKorisnika(@Param("korisnikId") Integer korisnikId, @Param("zahtjevId") Integer zahtjevId);
}
