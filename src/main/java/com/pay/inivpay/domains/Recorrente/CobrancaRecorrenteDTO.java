package com.pay.inivpay.domains.Recorrente;

import java.math.BigDecimal;

public record CobrancaRecorrenteDTO (BigDecimal valorRecorrencia,
                                     CobrancaRecorrenteEnum tipoRecorrencia,
                                     String dataInicio, Long usuarioId) {
}
