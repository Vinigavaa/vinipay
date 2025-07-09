package com.pay.inivpay.exceptions;

public class CobrancaNotFoundException extends RuntimeException {
  public CobrancaNotFoundException(String id) {
    super("Cobrança não encontrada ou Inválida!");
  }
}

