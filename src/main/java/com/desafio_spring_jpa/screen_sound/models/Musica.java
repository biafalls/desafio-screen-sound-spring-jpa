package com.desafio_spring_jpa.screen_sound.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Musica(String titulo, Album album) {
        this.titulo = titulo;
        this.album = album;
    }

    public Musica() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return id + " - '" + titulo + '\'' +
                " - " + album + '}';
    }
}
