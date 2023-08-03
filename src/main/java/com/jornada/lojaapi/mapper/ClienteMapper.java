package com.jornada.lojaapi.mapper;

import com.jornada.lojaapi.dto.ClienteDTO;
import com.jornada.lojaapi.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "codigo", target = "idCliente")
    Cliente converteParaEntity(ClienteDTO dto);

    // converter entity em dto
    @Mapping(source = "idCliente", target = "codigo")
    ClienteDTO converteParaDto(Cliente entity);
}
