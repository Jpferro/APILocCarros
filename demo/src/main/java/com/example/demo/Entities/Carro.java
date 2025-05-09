package com.example.demo.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "carros")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, unique = true)
    private String marca;

    @Column(nullable = false, unique = true)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private Boolean disponivel;

    @Column(nullable = false)
    private BigDecimal precoDiaria;
}

