package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
package com.example.demo.controller;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.service.ReservaService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.Entities.Reserva;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Reservas", description = "Endpoints para gerenciamento de reservas")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaMapper reservaMapper;

    @Operation(summary = "Lista todas as reservas", description = "Retorna uma lista de todas as reservas realizadas")
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas() {
        List<ReservaDTO> reservas = reservaService.listarTodas();
        return ResponseEntity.ok(reservas);
    }

    @Operation(summary = "Busca uma reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Long id) {
        Optional<ReservaDTO> reserva = reservaService.buscarPorId(id);
        return reserva.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova reserva")
    @PostMapping
    public ResponseEntity<ApiResponse<ReservaDTO>> criarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO reservaCriada = reservaService.criarReserva(reservaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(reservaCriada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(new ErrorResponse("Argumento inválido", e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(new ErrorResponse("Erro ao criar reserva", e.getMessage())));
        }
    }

    @Operation(summary = "Atualiza uma reserva existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservaDTO>> atualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO atualizada = reservaService.atualizarReserva(id, reservaDTO);
            return ResponseEntity.ok(new ApiResponse<>(atualizada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(new ErrorResponse("Reserva não encontrada", e.getMessage())));
        }
    }

    @Operation(summary = "Deleta uma reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoDTO toDTO(Pagamento pagamento);

    Pagamento toEntity(PagamentoDTO pagamentoDTO);

    List<PagamentoDTO> toDTOList(List<Pagamento> pagamentos);
}