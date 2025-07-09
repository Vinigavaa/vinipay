package com.pay.inivpay.domains.cobranca;

public enum CobrancaStatusEnum {
    EM_ABERTO("Aberto"),
    PAGO("Pago"),
    ATRASADO("Atrasado"),
    CANCELADO("Cancelado");

    private final String descricao;

    CobrancaStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
