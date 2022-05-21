package rs.sbnz.gunorama.model.facts;

import rs.sbnz.gunorama.model.Oruzje;

import java.util.List;

public class PreporucenoOruzjeFact {

    List<Oruzje> preporucenoOruzje;

    public PreporucenoOruzjeFact() {
    }

    public PreporucenoOruzjeFact(List<Oruzje> preporucenoOruzje) {
        this.preporucenoOruzje = preporucenoOruzje;
    }

    public List<Oruzje> getPreporucenoOruzje() {
        return preporucenoOruzje;
    }

    public void setPreporucenoOruzje(List<Oruzje> preporucenoOruzje) {
        this.preporucenoOruzje = preporucenoOruzje;
    }
}
