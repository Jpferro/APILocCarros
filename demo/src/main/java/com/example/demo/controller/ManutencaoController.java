package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ManutencaoDTO;
import com.example.demo.service.ManutencaoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Manutenções", description = "Endpoints para gerenciamento de manutenções de carros")
@RestController
@RequestMapping("api/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @Operation(summary = "Lista todas as manutenções", description = "Retorna uma lista com todos os registros de manutenção")
    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> listarManutencoes() {
        return ResponseEntity.ok(manutencaoService.listarTodos());
    }

    @Operation(summary = "Busca uma manutenção por ID", description = "Retorna os detalhes de uma manutenção específica")
    @GetMapping("/{id}")
    public ResponseEntity<ManutencaoDTO> buscarPorId(@PathVariable Long id) {
        Optional<ManutencaoDTO> manutencaoDTO = manutencaoService.buscarPorId(id);
        return manutencaoDTO.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registra uma nova manutenção", description = "Cadastra um novo registro de manutenção")
    @PostMapping
    public ResponseEntity<ApiResponse<ManutencaoDTO>> criarManutencao(@Valid @RequestBody ManutencaoDTO dto) {
        try {
            ManutencaoDTO saved = manutencaoService.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(new ErrorResponse("Argumento inválido", e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ApiResponse<>(new ErrorResponse("Erro interno", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta uma manutenção", description = "Remove um registro de manutenção pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        manutencaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}