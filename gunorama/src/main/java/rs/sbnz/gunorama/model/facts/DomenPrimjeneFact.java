package rs.sbnz.gunorama.model.facts;

import rs.sbnz.gunorama.model.enums.DomenPrimjene;

public class DomenPrimjeneFact {

    private DomenPrimjene domenPrimjene;

    private Integer zahtjevId;


    public DomenPrimjeneFact() {
    }

    public DomenPrimjeneFact(DomenPrimjene domenPrimjene, Integer zahtjevId) {
        this.domenPrimjene = domenPrimjene;
        this.zahtjevId = zahtjevId;
    }


    public DomenPrimjene getDomenPrimjene() {
        return domenPrimjene;
    }

    public void setDomenPrimjene(DomenPrimjene domenPrimjene) {
        this.domenPrimjene = domenPrimjene;
    }

    public Integer getZahtjevId() {
        return zahtjevId;
    }

    public void setZahtjevId(Integer zahtjevId) {
        this.zahtjevId = zahtjevId;
    }
}
