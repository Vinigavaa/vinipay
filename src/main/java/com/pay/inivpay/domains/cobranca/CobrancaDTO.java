package com.pay.inivpay.domains.cobranca;

import java.math.BigDecimal;

public record CobrancaDTO(BigDecimal valorCobranca, Long usuarioId) {
}
