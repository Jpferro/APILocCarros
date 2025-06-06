package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.service.PagamentoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pagamentos", description = "Endpoints para gerenciamento de pagamentos")
@RestController
@RequestMapping("api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Lista todos os pagamentos", description = "Retorna todos os registros de pagamento")
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @Operation(summary = "Busca um pagamento por ID", description = "Retorna os detalhes de um pagamento específico")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) {
        Optional<PagamentoDTO> pagamentoDTO = pagamentoService.buscarPorId(id);
        return pagamentoDTO.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registra um novo pagamento", description = "Cadastra um novo pagamento realizado por um cliente")
    @PostMapping
    public ResponseEntity<ApiResponse<PagamentoDTO>> criarPagamento(@Valid @RequestBody PagamentoDTO dto) {
        try {
            PagamentoDTO saved = pagamentoService.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Argumento inválido", e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ApiResponse<>(new ErrorResponse("Erro interno", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta um pagamento", description = "Remove um registro de pagamento pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
