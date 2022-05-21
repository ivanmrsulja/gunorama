package rs.sbnz.gunorama.model.faze;

import rs.sbnz.gunorama.model.enums.Dokument;

import java.util.ArrayList;
import java.util.List;

public class DomenPrimjeneFaza extends Faza{

    private List<Dokument> dokumenti = new ArrayList<>();


    public DomenPrimjeneFaza(List<Dokument> dokumenti, Integer zahtjevId) {
        super(zahtjevId);
        this.dokumenti = dokumenti;
    }


    public List<Dokument> getDokumenti() {
        return dokumenti;
    }

    public void setDokumenti(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }


}
