package com.example.demo.controller;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.Entities.Cliente;
import com.example.demo.Services.ClienteService;
import com.example.demo.mapper.ClienteMapper;
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

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteMapper.toDTOList(clienteService.listarTodos());
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Busca um cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(c -> ResponseEntity.ok(clienteMapper.toDTO(c)))
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo cliente")
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente salvo = clienteService.criarCliente(clienteMapper.toEntity(clienteDTO));
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(clienteMapper.toDTO(salvo)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(new ErrorResponse("Erro ao criar cliente", e.getMessage())));
        }
    }

    @Operation(summary = "Atualiza um cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente atualizado = clienteService.atualizarCliente(id, clienteMapper.toEntity(clienteDTO));
            return ResponseEntity.ok(new ApiResponse<>(clienteMapper.toDTO(atualizado)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(new ErrorResponse("Cliente não encontrado", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta um cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
