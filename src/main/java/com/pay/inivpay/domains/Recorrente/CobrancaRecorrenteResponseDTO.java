package com.pay.inivpay.domains.Recorrente;

import java.math.BigDecimal;

public record CobrancaRecorrenteResponseDTO(Long id,BigDecimal valorRecorrencia,
                                            CobrancaRecorrenteEnum tipoRecorrencia,
                                            String dataInicio, Long usuarioId) {
    public static CobrancaRecorrenteResponseDTO from(CobrancaRecorrente cobrancaRecorrente) {
        return new CobrancaRecorrenteResponseDTO(
                cobrancaRecorrente.getId(),
                cobrancaRecorrente.getValorRecorrencia(),
                cobrancaRecorrente.getTipoRecorrencia(),
                cobrancaRecorrente.getDataInicio() != null ? cobrancaRecorrente.getDataInicio().toString() : null,
                cobrancaRecorrente.getUsuario().getId()
        );
    }
}
