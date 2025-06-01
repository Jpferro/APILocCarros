package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PagamentoDTO {

    private Long id;

    @NotBlank(message = "O valor é obrigatório")
    private BigDecimal valor;

    @NotBlank(message = "A data de pagamento é obrigatória")
    private LocalDateTime dataPagamento;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "A forma de pagamento é obrigatória")
    private String formaPagamento;
}
