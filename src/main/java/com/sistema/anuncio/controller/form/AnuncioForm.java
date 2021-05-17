package com.sistema.anuncio.controller.form;

import com.sistema.anuncio.model.Anuncio;

import java.time.LocalDate;
import java.util.Date;

public class AnuncioForm {

    private String nome;
    private String cliente;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private double investimentoDia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getInvestimentoDia() {
        return investimentoDia;
    }

    public void setInvestimentoDia(double investimentoDia) {
        this.investimentoDia = investimentoDia;
    }

    public Anuncio converter() {
        return new Anuncio(nome, cliente, dataInicio, dataTermino, investimentoDia);
    }
}
