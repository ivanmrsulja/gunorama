package rs.sbnz.gunorama.model.facts;

public class GradjaninZdravstvenoSposobanFact {

    private Integer zahtjevId;

    private boolean sposoban;

    public GradjaninZdravstvenoSposobanFact() {}

    public GradjaninZdravstvenoSposobanFact(Integer zahtjevId, boolean sposoban) {
        this.zahtjevId = zahtjevId;
        this.sposoban = sposoban;
    }

    public Integer getZahtjevId() {
        return zahtjevId;
    }

    public void setZahtjevId(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }

    public boolean isSposoban() {
        return sposoban;
    }

    public void setSposoban(boolean sposoban) {
        this.sposoban = sposoban;
    }
}
