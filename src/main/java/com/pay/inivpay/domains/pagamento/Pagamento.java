package com.pay.inivpay.domains.pagamento;

import com.pay.inivpay.domains.cobranca.Cobranca;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cobranca_id", nullable = false)
    private Cobranca cobranca;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private PagamentoTipoEnum tipo;
}
