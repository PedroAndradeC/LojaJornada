package com.jornada.lojaapi.mapper;

import com.jornada.lojaapi.dto.VendaDTO;
import com.jornada.lojaapi.entity.Venda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    Venda converteParaEntity(VendaDTO dto);

    // converter entity em dto
    VendaDTO converteParaDto(Venda entity);
}

