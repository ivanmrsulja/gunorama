package rs.sbnz.gunorama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DozvolaUpitnik {

    @NotBlank
    private String jmbgKorisnika;

    @Email
    private String emailKorisnika;

    private Double dioptrija;

    private boolean prosaoPsiholoskuEvaluaciju;

    private boolean prosaoPsihijatrijskuEvaluaciju;

    @NotNull
    private List<TipDokumenta> dokumenta;
}
