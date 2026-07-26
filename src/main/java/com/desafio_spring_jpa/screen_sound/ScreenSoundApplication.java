package com.desafio_spring_jpa.screen_sound;

import com.desafio_spring_jpa.screen_sound.principal.Principal;
import com.desafio_spring_jpa.screen_sound.repository.AlbumRepository;
import com.desafio_spring_jpa.screen_sound.repository.ArtistaRepository;
import com.desafio_spring_jpa.screen_sound.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository,
				albumRepository, musicaRepository);
		principal.exibirMenu();
	}
}
