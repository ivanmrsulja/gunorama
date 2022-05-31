package rs.sbnz.gunorama.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Role(Role.Type.EVENT)
@Expires("30m")
public class DetektovanaNepravilnost {

    private int korisnikId;

    private int zahtjevId;

    private boolean obradjena;
}
