package rs.sbnz.gunorama.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnickiUpitnik {


    private Double tezinaDivljaci;

    private Double daljinaMete;

    private Boolean skitIzabran;

    private Boolean trapIzabran;

    private Boolean nosenjeIzabrano;

    private Boolean posjedovanjeIzabrano;
}
