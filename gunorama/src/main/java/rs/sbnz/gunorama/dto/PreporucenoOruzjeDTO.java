package rs.sbnz.gunorama.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.KonkretnaNamjena;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreporucenoOruzjeDTO {

    List<OruzjeDTO> preporucenoOruzje = new ArrayList<>();

    List<KalibarDTO> dozvoljeniKalibri = new ArrayList<>();

    KonkretnaNamjena konkretnaNamjena;
}
