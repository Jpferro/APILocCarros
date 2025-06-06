package com.example.demo.controller;

import com.example.demo.dto.CarroDTO;
import com.example.demo.Services.CarroService;
import com.example.demo.Entities.Carro;
import com.example.demo.mapper.CarroMapper;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Carros", description = "Endpoints para gerenciamento de carros")
@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService carroService;
    private final CarroMapper carroMapper;

    public CarroController(CarroService carroService, CarroMapper carroMapper) {
        this.carroService = carroService;
        this.carroMapper = carroMapper;
    }

    @Operation(summary = "Lista todos os carros")
    @GetMapping
    public ResponseEntity<List<CarroDTO>> listarCarros() {
        List<CarroDTO> carros = carroMapper.toDTOList(carroService.listarTodos());
        return ResponseEntity.ok(carros);
    }

    @Operation(summary = "Busca um carro por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> buscarPorId(@PathVariable Long id) {
        Optional<Carro> carro = carroService.buscarPorId(id);
        return carro.map(c -> ResponseEntity.ok(carroMapper.toDTO(c)))
                    .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo carro")
    @PostMapping
    public ResponseEntity<ApiResponse<CarroDTO>> criarCarro(@Valid @RequestBody CarroDTO carroDTO) {
        try {
            Carro carroSalvo = carroService.criarCarro(carroMapper.toEntity(carroDTO));
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(carroMapper.toDTO(carroSalvo)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(new ErrorResponse("Erro ao criar carro", e.getMessage())));
        }
    }

    @Operation(summary = "Atualiza um carro existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CarroDTO>> atualizarCarro(@PathVariable Long id, @Valid @RequestBody CarroDTO carroDTO) {
        try {
            Carro atualizado = carroService.atualizarCarro(id, carroMapper.toEntity(carroDTO));
            return ResponseEntity.ok(new ApiResponse<>(carroMapper.toDTO(atualizado)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(new ErrorResponse("Carro não encontrado", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta um carro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        carroService.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }
}
