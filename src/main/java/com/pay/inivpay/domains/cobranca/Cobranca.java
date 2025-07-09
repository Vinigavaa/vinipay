package com.pay.inivpay.domains.cobranca;

import com.pay.inivpay.domains.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cobranca")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cobranca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_cobranca", nullable = false)
    private BigDecimal valorCobranca;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CobrancaStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
