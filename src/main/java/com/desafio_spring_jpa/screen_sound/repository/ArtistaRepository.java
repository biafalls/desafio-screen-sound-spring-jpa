package com.desafio_spring_jpa.screen_sound.repository;

import com.desafio_spring_jpa.screen_sound.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeIgnoreCase(String nome);

}
