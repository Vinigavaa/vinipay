package com.pay.inivpay.domains.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoResponseDTO(UUID id, Long cobrancaId, BigDecimal valor, PagamentoTipoEnum tipo) {
    public static PagamentoResponseDTO from(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getCobranca() != null ? pagamento.getCobranca().getId() : null,
                pagamento.getValor(),
                pagamento.getTipo()
        );
    }
}
