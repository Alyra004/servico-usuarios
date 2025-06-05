package com.biblioteca.servico_usuarios.service;

import org.springframework.stereotype.Service;

import com.biblioteca.servico_usuarios.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GestaoUsuario {

    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        if(buscarUsuarioPorId(novoUsuario.getId()).isPresent()){
            return null;
        }
        this.usuarios.add(novoUsuario);
        return novoUsuario;
    }

    public Optional<Usuario> buscarUsuarioPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public List<Usuario> listarTodosUsuarios() {
        return this.usuarios;
    }

    public boolean removerUsuario(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Optional<Usuario> atualizarSaldoDevedor(String id, double valor) {
        return buscarUsuarioPorId(id).map(usuario -> {
            usuario.setSaldoDevedor(usuario.getSaldoDevedor() + valor);
            usuario.setInadimplente(usuario.getSaldoDevedor() > 0);
            return usuario;
        });
    }

    public Optional<Boolean> verificarInadimplenciaPorId(String id) {
    return buscarUsuarioPorId(id).map(Usuario::isInadimplente);
    }

}