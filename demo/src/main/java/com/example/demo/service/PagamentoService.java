package com.example.demo.service;

import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    public List<PagamentoDTO> listarTodos() {
        return pagamentoMapper.toDTOList(pagamentoRepository.findAll());
    }

    public Optional<PagamentoDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id).map(pagamentoMapper::toDTO);
    }

    public PagamentoDTO salvar(PagamentoDTO dto) {
        Pagamento entity = pagamentoMapper.toEntity(dto);
        return pagamentoMapper.toDTO(pagamentoRepository.save(entity));
    }

    public void deletar(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
