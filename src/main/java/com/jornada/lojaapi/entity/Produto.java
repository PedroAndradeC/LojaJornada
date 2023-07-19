package com.jornada.lojaapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Date;

public class Produto {

    @Schema(description = "código identificador do produto", example = "10")
    @Positive
    private Integer idProduto;
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
    private Date dataDeValidade;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}

