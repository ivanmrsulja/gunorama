package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZdravstvenoSposobanFaza extends Faza{

    private Double dioptrija;

    private boolean prosaoPsiholoskuEvaluaciju;

    private boolean prosaoPsihijatrijskuEvaluaciju;


    public ZdravstvenoSposobanFaza(Double dioptrija, Integer zahtjevId, boolean prosaoPsiholoskuEvaluaciju, boolean prosaoPsihijatrijskuEvaluaciju) {
        super(zahtjevId);
        this.dioptrija = dioptrija;
        this.zahtjevId = zahtjevId;
        this.prosaoPsiholoskuEvaluaciju = prosaoPsiholoskuEvaluaciju;
        this.prosaoPsihijatrijskuEvaluaciju = prosaoPsihijatrijskuEvaluaciju;
    }
}
