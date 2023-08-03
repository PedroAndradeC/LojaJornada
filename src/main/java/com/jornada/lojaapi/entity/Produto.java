package com.jornada.lojaapi.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Produto {

    private Integer idProduto;
    private String nome;
    private Integer quantidade;
    private Double preco;
    private Date dataDeValidade;
}

