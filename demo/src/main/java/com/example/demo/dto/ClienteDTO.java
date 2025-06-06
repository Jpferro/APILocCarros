package com.example.demo.dto;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> 925f7a31d9d562c88d54aac58916a292148c04e3

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

<<<<<<< HEAD
=======
import java.time.LocalDate;

>>>>>>> 925f7a31d9d562c88d54aac58916a292148c04e3
public class ClienteDTO {

  private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;

    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}



