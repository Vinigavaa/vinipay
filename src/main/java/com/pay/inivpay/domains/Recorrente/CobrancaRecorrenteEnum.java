package com.pay.inivpay.domains.Recorrente;

public enum CobrancaRecorrenteEnum {
    MENSAL("Mensal"),
    SEMANAL("Semanal");

    private final String descricao;

    CobrancaRecorrenteEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
