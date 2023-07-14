package com.jornada.lojaapi.dto;

import java.util.Date;

public class ErrorDTO {

    private Date data;
    private int codigoDeErro;
    private String messagem;

    public ErrorDTO(Date data, int codigoDeErro, String messagem) {
        this.data = data;
        this.codigoDeErro = codigoDeErro;
        this.messagem = messagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodigoDeErro() {
        return codigoDeErro;
    }

    public void setCodigoDeErro(int codigoDeErro) {
        this.codigoDeErro = codigoDeErro;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
