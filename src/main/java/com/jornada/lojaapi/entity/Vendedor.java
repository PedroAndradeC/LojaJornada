package com.jornada.lojaapi.entity;

import lombok.Data;

@Data
public class Vendedor {

    private Integer idVendedor;
    private String nome;
    private Long cpf;
    private Long telefone;
}
