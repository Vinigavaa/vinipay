package com.pay.inivpay.domains.pagamento;

import java.math.BigDecimal;

public record PagamentoDTO(Long cobrancaId,
                           BigDecimal valor,
                           PagamentoTipoEnum tipo) {
    }
