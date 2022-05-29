package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PreporucenoOruzjeFact extends Fact{

    List<Oruzje> preporucenoOruzje;

    Set<Kalibar> dozvoljeniKalibri;

    KonkretnaNamjena konkretnaNamjena;

    public PreporucenoOruzjeFact(Integer zahtjevId, List<Oruzje> preporucenoOruzje, Set<Kalibar> dozvoljeniKalibri, KonkretnaNamjena konkretnaNamjena) {
        super(zahtjevId);
        this.preporucenoOruzje = preporucenoOruzje;
        this.dozvoljeniKalibri = dozvoljeniKalibri;
        this.konkretnaNamjena = konkretnaNamjena;
    }
}
