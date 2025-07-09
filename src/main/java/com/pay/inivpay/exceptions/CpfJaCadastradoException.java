// src/main/java/com/pay/inivpay/exceptions/CpfJaCadastradoException.java
package com.pay.inivpay.exceptions;

public class CpfJaCadastradoException extends RuntimeException {
  public CpfJaCadastradoException(String cpf) {
    super("CPF já cadastrado: " + cpf);
  }
}