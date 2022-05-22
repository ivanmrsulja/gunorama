package rs.sbnz.gunorama.model.faze;

import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.List;

@Getter
@Setter
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

}
