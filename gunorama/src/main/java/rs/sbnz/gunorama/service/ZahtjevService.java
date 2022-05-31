package rs.sbnz.gunorama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.exception.NotFoundException;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.repository.ZahtjevRepository;

import java.util.List;

@Service
public class ZahtjevService {

    private final ZahtjevRepository zahtjevRepository;

    @Autowired
    public ZahtjevService(ZahtjevRepository zahtjevRepository) {
        this.zahtjevRepository = zahtjevRepository;
    }

    public List<Zahtjev> dobaviOdobreneZahtjeveZaKorisnika(Integer korisnikId) {
        return this.zahtjevRepository.dobaviOdobreneZahtjeveZaKorisnika(korisnikId);
    }

    public Zahtjev dobaviOdobrenZahtjevZaKorisnika(Integer korisnikId, Integer zahtjevId) {
        return this.zahtjevRepository.dobaviOdobrenZahtjevZaKorisnika(korisnikId, zahtjevId)
                .orElseThrow(() -> new NotFoundException(String.format("Odobreni zahtjev sa id-ijem: %d za korisnika: %d nije pronadjen.", zahtjevId, korisnikId)));
    }
}
