package rs.sbnz.gunorama.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dokument {

    private TipDokumenta tipDokumenta;

    private int prioritet;
}
