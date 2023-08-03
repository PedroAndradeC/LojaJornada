package com.jornada.lojaapi.mapper;

import com.jornada.lojaapi.dto.ProdutoDTO;
import com.jornada.lojaapi.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source = "codigo_produto", target = "idProduto")
    Produto converteParaEntity(ProdutoDTO dto);

    // converter entity em dto
    @Mapping(source = "idProduto", target = "codigo_produto")
    ProdutoDTO converteParaDto(Produto entity);
}
