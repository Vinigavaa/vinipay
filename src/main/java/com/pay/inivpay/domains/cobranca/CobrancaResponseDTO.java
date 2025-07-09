package com.pay.inivpay.domains.cobranca;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CobrancaResponseDTO(
        Long id,
        BigDecimal valorCobranca,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String status,
        Long usuarioId,
        String usuarioNome
) {
    public static CobrancaResponseDTO from(Cobranca cobranca) {
        return new CobrancaResponseDTO(
                cobranca.getId(),
                cobranca.getValorCobranca(),
                cobranca.getDataVencimento(),
                cobranca.getDataPagamento(),
                cobranca.getStatus().toString(),
                cobranca.getUsuario().getId(),
                cobranca.getUsuario().getNome()
        );
    }
}