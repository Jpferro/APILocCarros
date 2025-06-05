package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.StatusReserva;
import com.example.demo.dto.StatusReservaDTO;

@Mapper(componentModel = "spring")
public interface StatusReservaMapper {

    StatusReservaDTO toDTO(StatusReserva statusReserva);

    StatusReserva toEntity(StatusReservaDTO statusReservaDTO);

    List<StatusReservaDTO> toDTOList(List<StatusReserva> statusReservas);
}