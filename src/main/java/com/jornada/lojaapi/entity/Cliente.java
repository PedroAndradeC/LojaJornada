package com.jornada.lojaapi.entity;

import lombok.Data;

@Data
public class Cliente {

    private Integer idCliente;
    private String nome;
    private Long cpf;
    private Long telefone;
}
