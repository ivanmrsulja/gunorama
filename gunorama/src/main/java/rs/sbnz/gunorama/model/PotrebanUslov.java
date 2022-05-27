package rs.sbnz.gunorama.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.sbnz.gunorama.model.enums.DomenPrimjene;
import rs.sbnz.gunorama.model.enums.TipDokumenta;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "potrebni_uslovi")
public class PotrebanUslov extends BaseEntity {

    @Column(name = "usage_domain", nullable = false)
    private DomenPrimjene domenPrimjene;

    @ElementCollection(targetClass = TipDokumenta.class, fetch = FetchType.EAGER)
    @Column(name = "document_list", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<TipDokumenta> listaDokumenata;
}
