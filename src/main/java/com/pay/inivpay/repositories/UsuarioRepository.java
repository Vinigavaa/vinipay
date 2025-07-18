package com.pay.inivpay.repositories;

import com.pay.inivpay.domains.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Object> findByCpf(String cpf);
}
