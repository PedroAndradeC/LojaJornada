package com.jornada.lojaapi.mapper;

import com.jornada.lojaapi.dto.VendedorDTO;
import com.jornada.lojaapi.entity.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendedorMapper {

    @Mapping(source = "codigo_vendedor", target = "idVendedor")
    Vendedor converteParaEntity(VendedorDTO dto);

    // converter entity em dto
    @Mapping(source = "idVendedor", target = "codigo_vendedor")
    VendedorDTO converteParaDto(Vendedor entity);
}
