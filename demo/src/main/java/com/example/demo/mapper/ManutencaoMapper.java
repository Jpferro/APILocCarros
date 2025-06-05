package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Manutencao;
import com.example.demo.dto.ManutencaoDTO;

@Mapper(componentModel = "spring")
public interface ManutencaoMapper {

    ManutencaoDTO toDTO(Manutencao manutencao);

    Manutencao toEntity(ManutencaoDTO manutencaoDTO);

    List<ManutencaoDTO> toDTOList(List<Manutencao> manutencoes);
}
