package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Oruzje;

import java.util.List;

@Getter
@Setter
public class PreporucenoOruzjeFact {

    List<Oruzje> preporucenoOruzje;

    public PreporucenoOruzjeFact() {}

    public PreporucenoOruzjeFact(List<Oruzje> preporucenoOruzje) {
        this.preporucenoOruzje = preporucenoOruzje;
    }
}
