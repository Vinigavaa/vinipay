package com.pay.inivpay.controller;

import com.pay.inivpay.domains.cobranca.CobrancaDTO;
import com.pay.inivpay.domains.cobranca.CobrancaResponseDTO;
import com.pay.inivpay.exceptions.CobrancaNotFoundException;
import com.pay.inivpay.exceptions.UsuarioNotFoundException;
import com.pay.inivpay.services.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {
    @Autowired
    private CobrancaService cobrancaService;

    @PostMapping
    public ResponseEntity<CobrancaResponseDTO> createCobranca(@RequestBody CobrancaDTO cobrancaDTO) {
        CobrancaResponseDTO newCobranca = this.cobrancaService.createCobranca(cobrancaDTO);
        return ResponseEntity.ok(newCobranca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CobrancaResponseDTO> updateCobranca(@PathVariable Long id, @RequestBody CobrancaDTO cobrancaDTO) {
        CobrancaResponseDTO updatedCobranca = this.cobrancaService.updateCobranca(id, cobrancaDTO);
        return ResponseEntity.ok(updatedCobranca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CobrancaResponseDTO> getCobrancaById(@PathVariable Long id) {
        CobrancaResponseDTO cobranca = this.cobrancaService.getCobrancaById(id);
        return ResponseEntity.ok(cobranca);
    }

    @GetMapping
    public ResponseEntity<List<CobrancaResponseDTO>> getAllCobrancas() {
        return ResponseEntity.ok(this.cobrancaService.getAllCobrancas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCobranca(@PathVariable Long id) {
        this.cobrancaService.deleteCobranca(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CobrancaNotFoundException.class)
    public ResponseEntity<String> CobrancaNotFoundException(CobrancaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}