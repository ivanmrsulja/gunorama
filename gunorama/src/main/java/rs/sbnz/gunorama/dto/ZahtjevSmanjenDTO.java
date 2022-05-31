package rs.sbnz.gunorama.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.Zahtjev;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtjevSmanjenDTO {

    private Integer id;

    private DomenPrimjene domenPrimjene;


    public ZahtjevSmanjenDTO(Zahtjev zahtjev) {
        this.id = zahtjev.getId();
        this.domenPrimjene = zahtjev.getDomenPrimjene();
    }




}
