package com.sistema.anuncio.controller.dto;

import com.sistema.anuncio.model.Anuncio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioDto {

    private Long id;
    private String nome;
    private String cliente;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private double investimentoDia;

    public AnuncioDto (Anuncio anuncio) {
        this.id = anuncio.getId();
        this.nome = anuncio.getNome();
        this.cliente = anuncio.getCliente();
        this.dataInicio = anuncio.getDataInicio();
        this.dataTermino = anuncio.getDataTermino();
        this.investimentoDia = anuncio.getInvestimentoDia();
    }

    public static List<AnuncioDto> converter(List<Anuncio> anuncios) {
        return anuncios.stream().map(AnuncioDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public double getInvestimentoDia() {
        return investimentoDia;
    }
}
