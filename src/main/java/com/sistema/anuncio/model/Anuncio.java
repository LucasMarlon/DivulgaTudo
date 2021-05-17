package com.sistema.anuncio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
public class Anuncio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cliente;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private double investimentoDia;

    public Anuncio () {

    }

    public Anuncio(String nome, String cliente, LocalDate dataInicio, LocalDate dataTermino, double investimentoDia) {
        this.nome = nome;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.investimentoDia = investimentoDia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getInvestimentoDia() {
        return investimentoDia;
    }

    public void setInvestimentoDia(double investimentoDia) {
        this.investimentoDia = investimentoDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anuncio anuncio = (Anuncio) o;
        return Objects.equals(id, anuncio.id) &&
                Objects.equals(nome, anuncio.nome) &&
                Objects.equals(cliente, anuncio.cliente) &&
                Objects.equals(dataInicio, anuncio.dataInicio) &&
                Objects.equals(dataTermino, anuncio.dataTermino) &&
                Objects.equals(investimentoDia, anuncio.investimentoDia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cliente, dataInicio, dataTermino, investimentoDia);
    }

    /**
     * O método calcula o valor toral investido no anúncio com base no período do anúnico e
     * o valor investido por dia.
     * @param intervaloDias - Representa uma quantidade de dias dentro do período do anúncio.
     * @return O valor total investido no anúncio.
     */
    public double valorTotalInvestido(Integer intervaloDias) {
        Period period = Period.between(this.getDataInicio(), this.getDataTermino());
        // Quantidade de dias do anúncio
        int diasAnuncio = Math.abs(period.getDays()) + 1;

        if (intervaloDias != null && intervaloDias < diasAnuncio) {
            return intervaloDias * this.getInvestimentoDia();
        }

        return diasAnuncio * this.getInvestimentoDia();
    }

    /**
     * O método calcula a quantidade de pessoas que visualizam o anúncio original, sabendo que 30 pessoas visualizam
     * o anúncio original (não compartilhado) a cada R$ 1,00 investido.
     * @param intervaloDias - Representa uma quantidade de dias dentro do período do anúncio.
     * @return A quantidade de visuazações do anúncio original.
     */
    public double qtdVisualizacoesAnuncioOriginal(Integer intervaloDias) {
        return this.valorTotalInvestido(intervaloDias) * 30;
    }

    /**
     * O método calcula o número cliques do anúncio, com base nos dados de visualizações.
     * @param visualizacoes - Quantidade de pessoas que visualizaram o anúncio.
     * @return O alcance de cliques do anúncio.
     */
    public double alcanceCliques(double visualizacoes) {
        // A cada 100 pessoas que visualizam o anúncio 12 clicam nele
        return visualizacoes * 0.12;
    }

    /**
     * O método calcula o número de compartilhamentos do anúncio, com base nos dados visualizaçãoes e cliques.
     * @param visualizacoes - Quantidade de pessoas que visualizaram o anúnico.
     * @return O alcance de compartilhamentos do anúncio.
     */
    public double alcanceCompartilhamento(double visualizacoes) {
        double cliques = alcanceCliques(visualizacoes);

        // A cada 20 pessoas que clicam no anúncio 3 compartilham nas redes sociais
        return cliques * 0.15;
    }

    /**
     * O médoto calcula a quantidade máxima de compartilhamentos do anúncio.
     * @param intervaloDias - Representa uma quantidade de dias dentro do período do anúncio.
     * @return A quantidade máxima de compartilhamentos do anúncio.
     */
    public int qtdMaxCompartilhamentos(Integer intervaloDias) {
        double visualizacoes = qtdVisualizacoesAnuncioOriginal(intervaloDias);
        double compartilhamentosAcumulados = 0;
        double compartilhamentos = alcanceCompartilhamento(visualizacoes);

        while (compartilhamentos >= 1) {
            compartilhamentos = alcanceCompartilhamento(visualizacoes);
            compartilhamentosAcumulados += compartilhamentos;
            // Cada compartilhamento nas redes sociais gera 40 novas visualizações.
            visualizacoes = compartilhamentos * 40;
        }

        return (int) compartilhamentosAcumulados;
    }

    /**
     * O médoto calcula a quantidade máxima de visualizações do anúncio.
     * @param intervaloDias - Representa uma quantidade de dias dentro do período do anúncio.
     * @return A quantidade máxima de visualizações do anúncio.
     */
    public int qtdMaxVisualizacoes(Integer intervaloDias) {
        double visualizacoes = qtdVisualizacoesAnuncioOriginal(intervaloDias);
        double compartilhamentos = alcanceCompartilhamento(visualizacoes);
        double visualizacoesAcumuladas = visualizacoes;

        while (compartilhamentos >= 1) {
            compartilhamentos = alcanceCompartilhamento(visualizacoes);
            // Cada compartilhamento nas redes sociais gera 40 novas visualizações.
            visualizacoes = compartilhamentos * 40;
            visualizacoesAcumuladas += visualizacoes;
        }

        return (int) visualizacoesAcumuladas;
    }

    /**
     * O médoto calcula a quantidade máxima de cliques do anúncio.
     * @param intervaloDias - Representa uma quantidade de dias dentro do período do anúncio.
     * @return A quantidade máxima de cliques do anúncio.
     */
    public int qtdMaxCliques(Integer intervaloDias) {
        return (int) (qtdMaxVisualizacoes(intervaloDias) * 0.12);
    }

}