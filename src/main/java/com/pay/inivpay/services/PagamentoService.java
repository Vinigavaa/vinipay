package com.pay.inivpay.services;

import com.pay.inivpay.domains.cobranca.Cobranca;
import com.pay.inivpay.domains.cobranca.CobrancaStatusEnum;
import com.pay.inivpay.domains.pagamento.Pagamento;
import com.pay.inivpay.domains.pagamento.PagamentoDTO;
import com.pay.inivpay.domains.pagamento.PagamentoResponseDTO;
import com.pay.inivpay.exceptions.CobrancaNotFoundException;
import com.pay.inivpay.exceptions.PagamentoNotFoundException;
import com.pay.inivpay.exceptions.ValorInvalidoException;
import com.pay.inivpay.repositories.CobrancaRepository;
import com.pay.inivpay.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CobrancaRepository cobrancaRepository;

    public PagamentoResponseDTO createPagamento(PagamentoDTO data) {
        Cobranca cobranca = cobrancaRepository.findById(data.cobrancaId())
                .orElseThrow(() -> new CobrancaNotFoundException(" "));

        if (data.valor().compareTo(cobranca.getValorCobranca()) < 0) {
            throw new ValorInvalidoException(" ");
        }

        if (data.valor().compareTo(cobranca.getValorCobranca()) > 0) {
            throw new ValorInvalidoException(" ");
        }

        if (cobranca.getStatus() != CobrancaStatusEnum.EM_ABERTO) {
            throw new CobrancaNotFoundException(" ");
        }

        cobranca.setStatus(CobrancaStatusEnum.PAGO);
        cobranca.setDataPagamento(java.time.LocalDate.now());
        cobrancaRepository.save(cobranca);

        Pagamento newPagamento = new Pagamento();
        newPagamento.setValor(data.valor());
        newPagamento.setCobranca(cobranca);
        newPagamento.setTipo(data.tipo());
        Pagamento savedPagamento = pagamentoRepository.save(newPagamento);
        return PagamentoResponseDTO.from(savedPagamento);
    }

    public List<PagamentoResponseDTO> getAllPagamentos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(PagamentoResponseDTO::from)
                .toList();
    }

    public PagamentoResponseDTO getPagamentoById(UUID id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoNotFoundException(" "));
        return PagamentoResponseDTO.from(pagamento);
    }

    public void deletePagamento(UUID id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoNotFoundException(" "));

        Cobranca cobranca = pagamento.getCobranca();
        cobranca.setStatus(CobrancaStatusEnum.EM_ABERTO);
        cobranca.setDataPagamento(null);
        cobrancaRepository.save(cobranca);
        pagamentoRepository.delete(pagamento);
    }

    public Pagamento savePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }
}
