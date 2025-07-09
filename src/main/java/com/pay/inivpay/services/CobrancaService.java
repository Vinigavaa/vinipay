package com.pay.inivpay.services;

import com.pay.inivpay.domains.cobranca.Cobranca;
import com.pay.inivpay.domains.cobranca.CobrancaDTO;
import com.pay.inivpay.domains.cobranca.CobrancaResponseDTO;
import com.pay.inivpay.domains.cobranca.CobrancaStatusEnum;
import com.pay.inivpay.domains.usuario.Usuario;
import com.pay.inivpay.exceptions.CobrancaNotFoundException;
import com.pay.inivpay.exceptions.UsuarioNotFoundException;
import com.pay.inivpay.exceptions.ValorInvalidoException;
import com.pay.inivpay.repositories.CobrancaRepository;
import com.pay.inivpay.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CobrancaService {
    @Autowired
    private CobrancaRepository cobrancaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CobrancaResponseDTO createCobranca(CobrancaDTO data) {
        Usuario usuario = usuarioRepository.findById(data.usuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado!"));

        if(data.valorCobranca().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException(" ");
        }

        Cobranca cobranca = new Cobranca();
        cobranca.setValorCobranca(data.valorCobranca());
        cobranca.setStatus(CobrancaStatusEnum.EM_ABERTO);
        cobranca.setUsuario(usuario);
        cobranca.setDataPagamento(null);
        cobranca.setDataVencimento(java.time.LocalDate.now().plusDays(30));

        Cobranca savedCobranca = cobrancaRepository.save(cobranca);

        return CobrancaResponseDTO.from(savedCobranca);
    }

    public CobrancaResponseDTO updateCobranca(Long id, CobrancaDTO data) {
        Cobranca cobranca = cobrancaRepository.findById(id)
                .orElseThrow(() -> new CobrancaNotFoundException(" "));

        if (data.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(data.usuarioId())
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com ID: " + id));
            cobranca.setUsuario(usuario);
        }

        cobranca.setValorCobranca(data.valorCobranca());
        cobranca.setDataVencimento(java.time.LocalDate.now().plusDays(30));

        Cobranca updatedCobranca = cobrancaRepository.save(cobranca);
        return CobrancaResponseDTO.from(updatedCobranca);
    }

    public CobrancaResponseDTO getCobrancaById(Long id) {
        Cobranca cobranca = cobrancaRepository.findById(id)
                .orElseThrow(() -> new CobrancaNotFoundException(" "));
        return CobrancaResponseDTO.from(cobranca);
    }

    public List<CobrancaResponseDTO> getAllCobrancas() {
        List<Cobranca> cobrancas = cobrancaRepository.findAll();

        return cobrancas.stream()
                .map(CobrancaResponseDTO::from)
                .toList();
    }

    public void deleteCobranca(Long id) {
        Cobranca cobranca = cobrancaRepository.findById(id)
                .orElseThrow(() -> new CobrancaNotFoundException(" "));
        cobrancaRepository.delete(cobranca);
    }
}