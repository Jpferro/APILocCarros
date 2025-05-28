package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    
    
    List<Carro> findByDisponivelTrue();
}
