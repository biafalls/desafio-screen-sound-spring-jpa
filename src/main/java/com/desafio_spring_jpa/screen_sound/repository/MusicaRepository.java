package com.desafio_spring_jpa.screen_sound.repository;

import com.desafio_spring_jpa.screen_sound.models.Album;
import com.desafio_spring_jpa.screen_sound.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    boolean existsByTituloIgnoreCaseAndAlbum(String titulo, Album album);

    @Query("""
    SELECT m
    FROM Album al
    JOIN al.artista a
    JOIN al.musicas m
    WHERE LOWER(a.nome) = LOWER(:nome)
""")
    List<Musica> buscarMusicasPorArtista(String nome);

    @Query("""
    SELECT m
    FROM Album al
    JOIN al.musicas m
    WHERE LOWER(al.nome) = LOWER(:nome)
""")
    List<Musica> buscarMusicasPorAlbum(String nome);
}
