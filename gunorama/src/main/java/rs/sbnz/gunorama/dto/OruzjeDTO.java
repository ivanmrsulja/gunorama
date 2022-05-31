package rs.sbnz.gunorama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mvel2.ast.Or;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.model.Oruzje;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OruzjeDTO {

    private String naziv;

    private List<KalibarDTO> dozvoljeniKalibri;

    private MehanizamHranjenja mehanizamHranjenja;

    private MehanizamOkidanja mehanizamOkidanja;


    public OruzjeDTO(Oruzje oruzje) {
        this.naziv = oruzje.getNaziv();
        this.dozvoljeniKalibri = oruzje.getDozvoljeniKalibri().stream().map(KalibarDTO::new).collect(Collectors.toList());;
        this.mehanizamHranjenja = oruzje.getMehanizamHranjenja();
        this.mehanizamOkidanja = oruzje.getMehanizamOkidanja();
    }
}
