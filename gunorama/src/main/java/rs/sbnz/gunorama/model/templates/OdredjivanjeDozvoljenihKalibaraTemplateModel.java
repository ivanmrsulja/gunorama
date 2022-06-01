package rs.sbnz.gunorama.model.templates;


import lombok.Getter;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

@Getter
@Setter
public class OdredjivanjeDozvoljenihKalibaraTemplateModel {
    private KonkretnaNamjena zeljenaKonkretnaNamjena;
    private Double minimalniPrecnikUMilimetrima;
    private Double maksimalniPrecnikUMilimetrima;


    public OdredjivanjeDozvoljenihKalibaraTemplateModel(KonkretnaNamjena zeljenaKonkretnaNamjena, Double minimalniPrecnikUMilimetrima, Double maksimalniPrecnikUMilimetrima) {
        this.zeljenaKonkretnaNamjena = zeljenaKonkretnaNamjena;
        this.minimalniPrecnikUMilimetrima = minimalniPrecnikUMilimetrima;
        this.maksimalniPrecnikUMilimetrima = maksimalniPrecnikUMilimetrima;
    }
}
