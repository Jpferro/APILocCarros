package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    
    List<Usuario> findByDisponivelTrue();
}
