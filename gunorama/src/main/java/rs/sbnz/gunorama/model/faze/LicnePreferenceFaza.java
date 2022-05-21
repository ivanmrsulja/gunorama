package rs.sbnz.gunorama.model.faze;

import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.List;

public class LicnePreferenceFaza extends Faza {


    private List<MehanizamHranjenja> mehanizmiHranjenja;

    private List<MehanizamOkidanja> mehanizmiOkidanja;

    private List<Kalibar> kalibri;


    public LicnePreferenceFaza(Integer zahtjevId, List<MehanizamHranjenja> mehanizmiHranjenja, List<MehanizamOkidanja> mehanizmiOkidanja, List<Kalibar> kalibri) {
        super(zahtjevId);
        this.mehanizmiHranjenja = mehanizmiHranjenja;
        this.mehanizmiOkidanja = mehanizmiOkidanja;
        this.kalibri = kalibri;
    }

    public List<MehanizamHranjenja> getMehanizmiHranjenja() {
        return mehanizmiHranjenja;
    }

    public void setMehanizmiHranjenja(List<MehanizamHranjenja> mehanizmiHranjenja) {
        this.mehanizmiHranjenja = mehanizmiHranjenja;
    }

    public List<MehanizamOkidanja> getMehanizmiOkidanja() {
        return mehanizmiOkidanja;
    }

    public void setMehanizmiOkidanja(List<MehanizamOkidanja> mehanizmiOkidanja) {
        this.mehanizmiOkidanja = mehanizmiOkidanja;
    }

    public List<Kalibar> getKalibri() {
        return kalibri;
    }

    public void setKalibri(List<Kalibar> kalibri) {
        this.kalibri = kalibri;
    }
}
