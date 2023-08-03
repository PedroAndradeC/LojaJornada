package com.jornada.lojaapi.entity;

import lombok.Data;

@Data
public class Venda {

    private Integer idVenda;
    private Integer idProduto;
    private Integer idCliente;
    private Integer idVendedor;
    private Integer quantidade;
}
