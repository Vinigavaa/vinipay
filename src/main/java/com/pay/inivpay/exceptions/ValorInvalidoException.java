package com.pay.inivpay.exceptions;

public class ValorInvalidoException extends RuntimeException {
  public ValorInvalidoException(String id) {
    super("Valor Invalido! O valor deve ser maior que zero");
  }
}

