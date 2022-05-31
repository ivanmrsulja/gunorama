package rs.sbnz.gunorama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.repository.KorisnikRepository;


@Service
public class KorisnikService implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;


    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.korisnikRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException(String.format("Korisnik sa imenom: %s nije pronadjen.", username)));
    }
}
