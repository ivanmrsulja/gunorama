package rs.sbnz.gunorama.model.faze;

public abstract class Faza {

    protected Integer zahtjevId;

    protected Faza(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }

    public Integer getZahtjevId() {
        return zahtjevId;
    }

    public void setZahtjevId(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }
}
