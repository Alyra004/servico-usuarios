package com.biblioteca.servico_usuarios.service;

import com.biblioteca.servico_usuarios.model.Usuario;
import com.biblioteca.servico_usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        return usuarioRepository.save(novoUsuario);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(id);
    }
    
    public boolean removerUsuario(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Usuario> atualizarSaldoDevedor(String id, double valor) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setSaldoDevedor(usuario.getSaldoDevedor() + valor);
            usuario.setInadimplente(usuario.getSaldoDevedor() > 0);
            return usuarioRepository.save(usuario);
        });
    }

    public Optional<Boolean> verificarInadimplenciaPorId(String id) {
        return usuarioRepository.findById(id).map(Usuario::isInadimplente);
    }
}