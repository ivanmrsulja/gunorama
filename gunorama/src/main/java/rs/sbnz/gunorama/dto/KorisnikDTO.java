package rs.sbnz.gunorama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnikDTO {

    private String email;

    private String jmbg;

    private boolean prestupnik;
}
