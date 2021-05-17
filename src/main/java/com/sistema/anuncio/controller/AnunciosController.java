package com.sistema.anuncio.controller;

import com.sistema.anuncio.controller.dto.AnuncioDto;
import com.sistema.anuncio.controller.form.AnuncioForm;
import com.sistema.anuncio.model.Anuncio;
import com.sistema.anuncio.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anuncios")
public class AnunciosController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping
    public List<AnuncioDto> lista (String cliente) {
        if (cliente == null) {
            List<Anuncio> anuncios = anuncioRepository.findAll();
            return AnuncioDto.converter(anuncios);
        } else {
            List<Anuncio> anuncios = anuncioRepository.findByCliente(cliente);
            return AnuncioDto.converter(anuncios);
        }

    }

    @PostMapping
    public ResponseEntity<AnuncioDto> cadastrar (@RequestBody AnuncioForm anuncioForm, UriComponentsBuilder uriComponentsBuilder) {
        Anuncio anuncio = anuncioForm.converter();
        anuncioRepository.save(anuncio);

        URI uri = uriComponentsBuilder.path("/anuncios/{id}").buildAndExpand(anuncio.getId()).toUri();
        return ResponseEntity.created(uri).body(new AnuncioDto(anuncio));
    }

    @GetMapping({"/total/investido/{id}", "/total/investido/{id}/{periodo}"})
    public double valorTotalInvestido(@PathVariable(value = "id") Long id, @PathVariable(value = "periodo", required = false) Integer periodo) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get().valorTotalInvestido(periodo);
    }

    @GetMapping({"/visualizacoes/{id}", "/visualizacoes/{id}/{periodo}"})
    public int qtdMaxVisualizacoes(@PathVariable(value = "id") Long id, @PathVariable(value = "periodo", required = false) Integer periodo) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get().qtdMaxVisualizacoes(periodo);
    }

    @GetMapping({"/cliques/{id}", "/cliques/{id}/{periodo}"})
    public int qtdMaxCliques(@PathVariable(value = "id") Long id, @PathVariable(value = "periodo", required = false) Integer periodo) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get().qtdMaxCliques(periodo);
    }

    @GetMapping({"/compartilhamentos/{id}", "/compartilhamentos/{id}/{periodo}"})
    public int qtdMaxCompartilhamentos(@PathVariable(value = "id") Long id, @PathVariable(value = "periodo", required = false) Integer periodo) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get().qtdMaxCompartilhamentos(periodo);
    }

}
