package rs.sbnz.gunorama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DozvolaUpitnik {

    private String emailKorinika;

    private Double dioptrija;

    private boolean prosaoPsiholoskuEvaluaciju;

    private boolean prosaoPsihijatrijskuEvaluaciju;

    private List<TipDokumenta> dokumenta;
}
