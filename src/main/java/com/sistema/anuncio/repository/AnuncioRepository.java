package com.sistema.anuncio.repository;

import com.sistema.anuncio.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByCliente(String cliente);
}
