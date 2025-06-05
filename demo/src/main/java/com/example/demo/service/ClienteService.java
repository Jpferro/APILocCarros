package com.example.demo.service;

import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteDTO> listarTodos() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTO);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(salvo);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                cliente.setNome(clienteDTO.getNome());
                cliente.setCpf(clienteDTO.getCpf());
                cliente.setEmail(clienteDTO.getEmail());
                cliente.setSenha(clienteDTO.getSenha());
                cliente.setTelefone(clienteDTO.getTelefone());
                cliente.setDataNascimento(clienteDTO.getDataNascimento());
                return clienteMapper.toDTO(clienteRepository.save(cliente));
            })
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

