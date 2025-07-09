package com.pay.inivpay.domains.pagamento;

public enum PagamentoTipoEnum {
    PIX("PIX"),
    BOLETO("Boleto"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito");

    private final String descricao;

    PagamentoTipoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
