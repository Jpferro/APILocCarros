package com.example.demo.service;

import com.example.demo.Entities.Carro;
import com.example.demo.dto.CarroDTO;
import com.example.demo.mapper.CarroMapper;
import com.example.demo.Repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroMapper carroMapper;

    public List<CarroDTO> listarTodos() {
        return carroMapper.toDTOList(carroRepository.findAll());
    }

    public Optional<CarroDTO> buscarPorId(Long id) {
        return carroRepository.findById(id).map(carroMapper::toDTO);
    }

    public CarroDTO criarCarro(CarroDTO carroDTO) {
        Carro carro = carroMapper.toEntity(carroDTO);
        Carro salvo = carroRepository.save(carro);
        return carroMapper.toDTO(salvo);
    }

    public CarroDTO atualizarCarro(Long id, CarroDTO carroDTO) {
        return carroRepository.findById(id)
            .map(carro -> {
                carro.setModelo(carroDTO.getModelo());
                carro.setMarca(carroDTO.getMarca());
                carro.setAnoFabricacao(carroDTO.getAnoFabricacao());
                carro.setPlaca(carroDTO.getPlaca());
                carro.setDisponivel(carroDTO.getDisponivel());
                carro.setPrecoDiaria(carroDTO.getPrecoDiaria());
                return carroMapper.toDTO(carroRepository.save(carro));
            })
            .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
