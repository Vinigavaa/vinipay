package com.pay.inivpay.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String id) {
        super("Usuário não encontrado: " + id);
    }
}
