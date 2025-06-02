package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ManutencaoDTO {

    private Long id;

    @NotBlank(message = "O CarroId é obrigatório")
    private Long carroId;

    @NotBlank(message = "A descrição é obrigatória")
    private String desc;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "O data inicial é obrigatória")
    private LocalDateTime dataInicio;

    @NotBlank(message = "A data final é obrigatória")
    private LocalDateTime dataFim;
    
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}
