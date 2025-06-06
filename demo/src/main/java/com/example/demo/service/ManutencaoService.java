package com.example.demo.service;

import com.example.demo.Entities.Manutencao;
import com.example.demo.dto.ManutencaoDTO;
import com.example.demo.mapper.ManutencaoMapper;
import com.example.demo.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private ManutencaoMapper manutencaoMapper;

    public List<ManutencaoDTO> listarTodos() {
        return manutencaoMapper.toDTOList(manutencaoRepository.findAll());
    }

    public Optional<ManutencaoDTO> buscarPorId(Long id) {
        return manutencaoRepository.findById(id).map(manutencaoMapper::toDTO);
    }

    public ManutencaoDTO salvar(ManutencaoDTO dto) {
        Manutencao entity = manutencaoMapper.toEntity(dto);
        return manutencaoMapper.toDTO(manutencaoRepository.save(entity));
    }

    public void deletar(Long id) {
        manutencaoRepository.deleteById(id);
    }
}
