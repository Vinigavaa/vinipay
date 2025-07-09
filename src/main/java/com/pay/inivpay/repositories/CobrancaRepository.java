package com.pay.inivpay.repositories;

import com.pay.inivpay.domains.cobranca.Cobranca;
import com.pay.inivpay.domains.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {

}
