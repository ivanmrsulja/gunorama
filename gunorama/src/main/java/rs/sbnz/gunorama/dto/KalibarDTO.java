package rs.sbnz.gunorama.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.Kalibar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KalibarDTO {

    private Integer id;

    private String naziv;


    public KalibarDTO(Kalibar kalibar) {
        this.id = kalibar.getId();
        this.naziv = kalibar.getNaziv();
    }

}
