package com.biblioteca.servico_usuarios;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GestaoUsuario {

    private final List<Usuario> usuarios = new ArrayList<>();


    public Locatario cadastrarLocatario(Locatario novoLocatario) {
        if (buscarUsuarioPorId(novoLocatario.getId()).isPresent()) {
            return null;
        }
        this.usuarios.add(novoLocatario);
        return novoLocatario;
    }

    public Funcionario cadastrarFuncionario(Funcionario novoFuncionario) {
        if (buscarUsuarioPorId(novoFuncionario.getId()).isPresent()) {
            return null;
        }
        this.usuarios.add(novoFuncionario);
        return novoFuncionario;
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
}