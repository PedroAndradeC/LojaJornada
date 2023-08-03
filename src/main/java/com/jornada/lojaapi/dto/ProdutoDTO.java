package com.jornada.lojaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProdutoDTO {

    @Schema(description = "código identificador do produto", example = "10")
    @Positive
    private Integer codigo_produto;
    @NotNull
    @NotEmpty
    @Schema(description = "nome do produto", example = "feijão")
    private String nome;
    @Positive
    @Max(1000)
    @Schema(description = "quantidade do produto", example = "100")
    private Integer quantidade;
    @NotNull
    @Schema(description = "preço do produto", example = "12.59")
    private Double preco;
    @FutureOrPresent
    @Schema(description = "data de validade", example = "2025-09-10")
    private Date dataDeValidade;
}
