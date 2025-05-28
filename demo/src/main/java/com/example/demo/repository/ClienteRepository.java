package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    
    List<Cliente> findByDisponivelTrue();
}
