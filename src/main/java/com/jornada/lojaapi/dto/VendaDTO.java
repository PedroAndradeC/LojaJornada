package com.jornada.lojaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VendaDTO {

    @Positive
    @Schema(description = "código verificador de venda")
    private Integer idVenda;
    @Positive
    @Schema(description = "código verificador do produto")
    private Integer idProduto;
    @Positive
    @Schema(description = "código verificador do cliente")
    private Integer idCliente;
    @Positive
    @Schema(description = "código verificador do vendedor")
    private Integer idVendedor;
    @Positive
    @Schema(description = "quantidade do produto")
    private Integer quantidade;
}
