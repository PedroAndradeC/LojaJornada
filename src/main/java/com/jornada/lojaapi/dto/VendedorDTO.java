package com.jornada.lojaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VendedorDTO {

    @Positive
    @Schema(description = "código identificador do cliente", example = "1")
    private Integer codigo_vendedor;
    @Schema(description = "nome do cliente", example = "Pedro Andrade")
    @Size(min = 3, max = 30)
    private String nome;
    @Schema(description = "cpf do cliente", example = "123456789999")
    private Long cpf;
    @Schema(description = "número de telefone do cliente", example = "82999998888")
    private Long telefone;
}
