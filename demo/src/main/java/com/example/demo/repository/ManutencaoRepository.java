package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByDisponivelLisTrue();
}
