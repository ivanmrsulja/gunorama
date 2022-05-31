package rs.sbnz.gunorama.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.sbnz.gunorama.dto.AuthenticationRequestDTO;
import rs.sbnz.gunorama.dto.AuthenticationResponseDTO;
import rs.sbnz.gunorama.dto.ZahtjevSmanjenDTO;
import rs.sbnz.gunorama.service.ZahtjevService;
import rs.sbnz.gunorama.util.jwt.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/korisnici")
public class KorisnikController {

    private final AuthenticationManager authenticationManager;

    private final ZahtjevService zahtjevService;

    private final JwtUtil jwtUtil;

    @Autowired
    public KorisnikController(AuthenticationManager authenticationManager, ZahtjevService zahtjevService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.zahtjevService = zahtjevService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping(value = "/prijava")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDTO authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication);

        return new AuthenticationResponseDTO(token);
    }


    @GetMapping(value = "/{id}/odobreni-zahtjevi")
    @PreAuthorize("#id == authentication.principal.id")
    public List<ZahtjevSmanjenDTO> dobaviOdobreneZahtjeveZaKorisnika(@PathVariable("id") Integer id) {
        return this.zahtjevService.dobaviOdobreneZahtjeveZaKorisnika(id)
                .stream().map(ZahtjevSmanjenDTO::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/{korisnikId}/odobreni-zahtjevi/{zahtjevId}")
    @PreAuthorize("#korisnikId == authentication.principal.id")
    public ZahtjevSmanjenDTO dobaviZahtjev(@PathVariable("korisnikId") Integer korisnikId, @PathVariable("zahtjevId") Integer zahtjevId) {
        return new ZahtjevSmanjenDTO(this.zahtjevService.dobaviOdobrenZahtjevZaKorisnika(korisnikId, zahtjevId));
    }
}
