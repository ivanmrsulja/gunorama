package rs.sbnz.gunorama.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.Oruzje;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreporucenoOruzjeFact {

    List<Oruzje> preporucenoOruzje;

}
