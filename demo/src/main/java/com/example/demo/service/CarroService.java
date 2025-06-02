package com.example.demo.Services;

import com.example.demo.Entities.Carro;
import com.example.demo.Repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Carro criarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Carro atualizarCarro(Long id, Carro carroAtualizado) {
        return carroRepository.findById(id).map(carro -> {
            carro.setModelo(carroAtualizado.getModelo());
            carro.setMarca(carroAtualizado.getMarca());
            carro.setAnoFabricacao(carroAtualizado.getAnoFabricacao());
            carro.setPlaca(carroAtualizado.getPlaca());
            carro.setDisponivel(carroAtualizado.getDisponivel());
            carro.setPrecoDiaria(carroAtualizado.getPrecoDiaria());
            return carroRepository.save(carro);
        }).orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
