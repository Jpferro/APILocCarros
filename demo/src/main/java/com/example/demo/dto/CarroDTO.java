package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CarroDTO {
    
    private Long id;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A marca é obrigatório")
    private String marca;

    @NotBlank(message = "O ano de fabricação é obrigatório")
    private Integer anoFabricacao;

    @NotBlank(message = "A placa é obrigatória")
    @Size(min = 7, max = 7, message = "A placa deve ter pelo menos 7 caracteres")
    private String placa;

    private BigDecimal precoDiaria;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}
