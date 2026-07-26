package com.desafio_spring_jpa.screen_sound.repository;


import com.desafio_spring_jpa.screen_sound.models.Album;
import com.desafio_spring_jpa.screen_sound.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndArtista(String nome, Artista artista);

    @Query("""
    SELECT al
    FROM Album al
    WHERE LOWER(al.artista.nome) = LOWER(:nome)
""")
    List<Album> buscarAlbunsPorArtista(String nome);
}
