package com.pay.inivpay.controller;

import com.pay.inivpay.domains.usuario.UsuarioDTO;
import com.pay.inivpay.domains.usuario.UsuarioResponseDTO;
import com.pay.inivpay.exceptions.CpfJaCadastradoException;
import com.pay.inivpay.exceptions.UsuarioNotFoundException;
import com.pay.inivpay.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioResponseDTO newUsuario = this.usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.ok(newUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioResponseDTO updatedUsuario = this.usuarioService.updateUsuario(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        return ResponseEntity.ok(this.usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.getUsuarioResponseById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<String> handleCpfJaCadastrado(CpfJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
