package rs.sbnz.gunorama.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import rs.sbnz.gunorama.service.KorisnikService;
import rs.sbnz.gunorama.util.jwt.JwtFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final KorisnikService korisnikService;

    private final JwtFilter jwtFilter;

    private final PasswordEncoder passwordEncoder;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Autowired
    public SecurityConfig(KorisnikService korisnikService, JwtFilter jwtFilter, PasswordEncoder passwordEncoder, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.korisnikService = korisnikService;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.userDetailsService(korisnikService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String unosenjeZahtjeva = "UNOSENJE_ZAHTJEVA";
        final String popunjavanjeUpitnika = "POPUNJAVANJE_UPITNIKA";
        final String dobavljanjeZahtjevaZaKorisnika = "DOBAVLJANJE_ZAHTJEVA_ZA_KORISNIKA";

        http = http.cors().and().csrf().disable();

        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

        http.authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/korisnici/prijava").permitAll()
                .antMatchers(HttpMethod.GET, "/api/korisnici/{id}/odobreni-zahtjevi").hasAuthority(dobavljanjeZahtjevaZaKorisnika)
                .antMatchers(HttpMethod.GET, "/api/korisnici/{korisnikId}/odobreni-zahtjevi/{zahtjevId}").hasAuthority(dobavljanjeZahtjevaZaKorisnika)
                .antMatchers(HttpMethod.POST, "/api/korisnicki-upitnik").hasAuthority(popunjavanjeUpitnika)
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter,
                BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
