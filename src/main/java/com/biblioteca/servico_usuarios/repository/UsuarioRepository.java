package com.biblioteca.servico_usuarios.repository;

import com.biblioteca.servico_usuarios.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}