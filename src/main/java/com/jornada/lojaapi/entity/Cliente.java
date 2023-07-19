package com.jornada.lojaapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public class Cliente {

    @Schema(description = "código identificador do cliente", example = "5")
    @Positive
    private Integer idCliente;
    @Schema(description = "nome do cliente", example = "Pedro Andrade")
    private String nome;
    @Schema(description = "cpf do cliente", example = "123456789999")
    private Long cpf;
    @Schema(description = "número de telefone do cliente", example = "82999998888")
    private Long telefone;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
