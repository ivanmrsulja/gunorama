package rs.sbnz.gunorama.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnickiUpitnik {

    private Integer zahtjevId;

    private Double tezinaDivljaci;

    private Double daljinaMete;

    private Boolean skitIzabran;

    private Boolean trapIzabran;

    private Boolean nosenjeIzabrano;

    private Boolean posjedovanjeIzabrano;

    private List<MehanizamHranjenja> mehanizmiHranjenja;

    private List<MehanizamOkidanja> mehanizmiOkidanja;

    private List<Integer> kalibri; //id-ijevi zeljenih kalibara

    private Double minimalniPrecnikUMilimetrima = 0.0;

    private Double maksimalniPrecnikUMilimetrima = 15.0;

}
