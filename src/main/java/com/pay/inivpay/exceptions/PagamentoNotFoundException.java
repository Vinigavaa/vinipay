package com.pay.inivpay.exceptions;

public class PagamentoNotFoundException extends RuntimeException {
    public PagamentoNotFoundException(String id) {
        super("Pagamento não encontrado ou inválido! ID: " + id);
    }
}

