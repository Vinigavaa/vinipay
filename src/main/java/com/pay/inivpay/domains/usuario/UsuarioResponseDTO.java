// src/main/java/com/pay/inivpay/domains/usuario/UsuarioResponseDTO.java
package com.pay.inivpay.domains.usuario;

import java.util.List;
import java.util.stream.Collectors;

public record UsuarioResponseDTO(Long id, String nome, String cpf, List<Long> cobrancaIds) {
    public static UsuarioResponseDTO from(Usuario usuario) {
        List<Long> ids = usuario.getCobrancas() == null ? List.of() :
                usuario.getCobrancas().stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                ids
        );
    }
}