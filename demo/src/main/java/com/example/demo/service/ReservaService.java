package com.example.demo.service;

import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Carro;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private CarroRepository carroRepository;

    public List<ReservaDTO> listarTodos() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    public Optional<ReservaDTO> buscarPorId(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }

    public ReservaDTO salvar(ReservaDTO dto) {
        // Regra de negócio: só permite criar reserva se o carro estiver disponível
        Optional<Carro> carroOpt = carroRepository.findById(dto.getCarroId());
        if (carroOpt.isEmpty()) {
            throw new IllegalArgumentException("Carro não encontrado com ID: " + dto.getCarroId());
        }

        Carro carro = carroOpt.get();
        if (!carro.getDisponivel()) {
            throw new IllegalArgumentException("Carro não está disponível para reserva.");
        }

        // Marca o carro como indisponível (opcional, depende da lógica de negócios)
        carro.setDisponivel(false);
        carroRepository.save(carro);

        Reserva reserva = reservaMapper.toEntity(dto);
        reserva.setStatus("Confirmada"); // status inicial padrão

        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public void deletar(Long id) {
        reservaRepository.deleteById(id);
    }
}