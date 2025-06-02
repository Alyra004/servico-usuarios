package com.biblioteca.servico_usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/locatario")
    public ResponseEntity<Locatario> cadastrarLocatario(@RequestBody Locatario locatario) {
        Locatario novoLocatario = gestaoUsuario.cadastrarLocatario(locatario);
        if (novoLocatario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLocatario);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = gestaoUsuario.cadastrarFuncionario(funcionario);
        if (novoFuncionario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable String id) {
        if (gestaoUsuario.removerUsuario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}