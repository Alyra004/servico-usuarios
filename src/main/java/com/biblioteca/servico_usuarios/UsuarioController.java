package com.biblioteca.servico_usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios") // Define o caminho base para todos os endpoints nesta classe
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (ótimo para testes)
public class UsuarioController {

    // O Spring injeta a instância de GestaoUsuario automaticamente.
    @Autowired
    private GestaoUsuario gestaoUsuario;

    /**
     * Endpoint para listar todos os usuários.
     * Rota: GET /usuarios
     */
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return gestaoUsuario.listarTodosUsuarios();
    }

    /**
     * Endpoint para buscar um usuário específico pelo ID.
     * Rota: GET /usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String id) {
        return gestaoUsuario.buscarUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(usuario)) // Se encontrou, retorna 200 OK com o usuário
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    /**
     * Endpoint para cadastrar um novo Locatário.
     * Rota: POST /usuarios/locatario
     */
    @PostMapping("/locatario")
    public ResponseEntity<Locatario> cadastrarLocatario(@RequestBody Locatario locatario) {
        Locatario novoLocatario = gestaoUsuario.cadastrarLocatario(locatario);
        if (novoLocatario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLocatario); // Retorna 201 Created
        }
        return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request se o ID já existir
    }

    /**
     * Endpoint para cadastrar um novo Funcionário.
     * Rota: POST /usuarios/funcionario
     */
    @PostMapping("/funcionario")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = gestaoUsuario.cadastrarFuncionario(funcionario);
        if (novoFuncionario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint para remover um usuário pelo ID.
     * Rota: DELETE /usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable String id) {
        if (gestaoUsuario.removerUsuario(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se removeu com sucesso
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuário não existia
    }
}