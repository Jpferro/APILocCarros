package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    
    List<Reserva> findByDisponivelTrue();
}
