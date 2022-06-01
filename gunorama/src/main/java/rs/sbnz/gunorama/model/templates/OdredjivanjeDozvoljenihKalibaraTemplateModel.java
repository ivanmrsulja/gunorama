package rs.sbnz.gunorama.model.templates;


import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

@Getter
@Setter
public class OdredjivanjeDozvoljenihKalibaraTemplateModel {
    private KonkretnaNamjena zeljenaKonkretnaNamjena;


    public OdredjivanjeDozvoljenihKalibaraTemplateModel(KonkretnaNamjena zeljenaKonkretnaNamjena) {
        this.zeljenaKonkretnaNamjena = zeljenaKonkretnaNamjena;
    }
}
