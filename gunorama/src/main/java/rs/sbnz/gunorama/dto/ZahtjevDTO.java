package rs.sbnz.gunorama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtjevDTO {

    private KorisnikDTO korisnik;

    private boolean odobren = false;

    private String razlogOdbijanja = null;

    private DomenPrimjene domenPrimjene = null;

    private KonkretnaNamjena konkretnaNamjena = null;

    private String nepravilnost = null;

    private List<TipDokumenta> dokumenti = new ArrayList<>();
}
