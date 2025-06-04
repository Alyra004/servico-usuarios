package com.biblioteca.servico_usuarios.controller;

import com.biblioteca.servico_usuarios.model.Usuario;
import com.biblioteca.servico_usuarios.service.GestaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private GestaoUsuario gestaoUsuario;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return gestaoUsuario.listarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String id) {
        return gestaoUsuario.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint de cadastro unificado
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = gestaoUsuario.cadastrarUsuario(usuario);
        if (novoUsuario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // ID já existe
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable String id) {
        if (gestaoUsuario.removerUsuario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // O novo endpoint para atualizar saldo
    @PatchMapping("/{id}/saldo")
    public ResponseEntity<Usuario> atualizarSaldoDevedor(@PathVariable String id, @RequestBody Map<String, Double> updates) {
        Double valor = updates.get("valor");
        if (valor == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Usuario> usuarioAtualizado = gestaoUsuario.atualizarSaldoDevedor(id, valor);
        return usuarioAtualizado
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}