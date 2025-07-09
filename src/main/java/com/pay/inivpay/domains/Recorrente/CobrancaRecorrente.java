package com.pay.inivpay.domains.Recorrente;

import com.pay.inivpay.domains.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cobranca_recorrente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CobrancaRecorrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_recorrencia", nullable = false)
    private BigDecimal valorRecorrencia;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Enumerated
    @Column(name = "tipo_recorrencia", nullable = false)
    private CobrancaRecorrenteEnum tipoRecorrencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
