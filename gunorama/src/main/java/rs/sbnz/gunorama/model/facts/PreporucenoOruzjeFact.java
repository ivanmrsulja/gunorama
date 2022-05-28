package rs.sbnz.gunorama.model.facts;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Oruzje;

import java.util.List;

@Getter
@Setter
public class PreporucenoOruzjeFact extends Fact{

    List<Oruzje> preporucenoOruzje;

    public PreporucenoOruzjeFact(Integer zahtjevId, List<Oruzje> preporucenoOruzje) {
        super(zahtjevId);
        this.preporucenoOruzje = preporucenoOruzje;
    }
}
