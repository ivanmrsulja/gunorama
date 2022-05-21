package rs.sbnz.gunorama.model.faze;

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

    public Double getDioptrija() {
        return dioptrija;
    }

    public void setDioptrija(Double dioptrija) {
        this.dioptrija = dioptrija;
    }

    public boolean isProsaoPsiholoskuEvaluaciju() {
        return prosaoPsiholoskuEvaluaciju;
    }

    public void setProsaoPsiholoskuEvaluaciju(boolean prosaoPsiholoskuEvaluaciju) {
        this.prosaoPsiholoskuEvaluaciju = prosaoPsiholoskuEvaluaciju;
    }

    public boolean isProsaoPsihijatrijskuEvaluaciju() {
        return prosaoPsihijatrijskuEvaluaciju;
    }

    public void setProsaoPsihijatrijskuEvaluaciju(boolean prosaoPsihijatrijskuEvaluaciju) {
        this.prosaoPsihijatrijskuEvaluaciju = prosaoPsihijatrijskuEvaluaciju;
    }
}
