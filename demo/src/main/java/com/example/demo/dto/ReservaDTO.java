package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservaDTO {
    
    private Long id;

    @NotBlank(message = "O clienteId é obrigatório")
    private Long clienteId;

    @NotBlank(message = "O Id do carro é obrigatório")
    private Long carroId;

    @NotBlank(message = "A data de ínicio é obrigatório")
    private LocalDateTime dataInicio;

    @NotBlank(message = "A data final é obrigatória")
    private LocalDateTime dataFim;

    @NotBlank(message = "O status é obrigatório")
    private String status;
}
