package com.pay.inivpay.controller;

import com.pay.inivpay.domains.pagamento.Pagamento;
import com.pay.inivpay.domains.pagamento.PagamentoDTO;
import com.pay.inivpay.domains.pagamento.PagamentoResponseDTO;
import com.pay.inivpay.exceptions.CobrancaNotFoundException;
import com.pay.inivpay.exceptions.ValorInvalidoException;
import com.pay.inivpay.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> createPagamento(@RequestBody PagamentoDTO pagamentoDTO){
        PagamentoResponseDTO newPagamento = this.pagamentoService.createPagamento(pagamentoDTO);
        return ResponseEntity.ok(newPagamento);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> getAllPagamentos(){
        return ResponseEntity.ok(this.pagamentoService.getAllPagamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> getPagamentoById(@PathVariable UUID id) {
        PagamentoResponseDTO pagamento = this.pagamentoService.getPagamentoById(id);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable UUID id) {
        this.pagamentoService.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ValorInvalidoException.class)
    public ResponseEntity<String> ValorInvalidoException(ValorInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
