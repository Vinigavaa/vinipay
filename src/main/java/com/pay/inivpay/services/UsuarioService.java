package com.pay.inivpay.services;

import com.pay.inivpay.domains.usuario.Usuario;
import com.pay.inivpay.domains.usuario.UsuarioDTO;
import com.pay.inivpay.domains.usuario.UsuarioResponseDTO;
import com.pay.inivpay.exceptions.CpfJaCadastradoException;
import com.pay.inivpay.exceptions.UsuarioNotFoundException;
import com.pay.inivpay.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO createUsuario(UsuarioDTO data) {
        if (usuarioRepository.findByCpf(data.cpf()).isPresent()) {
            throw new CpfJaCadastradoException(data.cpf());
        }

        // Criação do usuário
        Usuario newUsuario = new Usuario();
        newUsuario.setNome(data.nome());
        newUsuario.setCpf(data.cpf());

        // Salvamento e retorno
        Usuario savedUsuario = usuarioRepository.save(newUsuario);
        return UsuarioResponseDTO.from(savedUsuario);
    }

    public UsuarioResponseDTO updateUsuario(Long id, UsuarioDTO data) {
        Usuario existingUsuario = getUsuarioById(id);
        existingUsuario.setNome(data.nome());
        if (!existingUsuario.getCpf().equals(data.cpf()) &&
                usuarioRepository.findByCpf(data.cpf()).isPresent()) {
            throw new CpfJaCadastradoException(data.cpf());
        }
        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return UsuarioResponseDTO.from(updatedUsuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(" "));
    }

    public UsuarioResponseDTO getUsuarioResponseById(Long id) {
        Usuario usuario = getUsuarioById(id);
        return UsuarioResponseDTO.from(usuario);
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::from)
                .toList();
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = getUsuarioById(id);
        usuarioRepository.delete(usuario);
    }
}