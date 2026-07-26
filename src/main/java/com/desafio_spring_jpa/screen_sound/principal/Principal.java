package com.desafio_spring_jpa.screen_sound.principal;



import com.desafio_spring_jpa.screen_sound.models.*;
import com.desafio_spring_jpa.screen_sound.repository.AlbumRepository;
import com.desafio_spring_jpa.screen_sound.repository.ArtistaRepository;
import com.desafio_spring_jpa.screen_sound.repository.MusicaRepository;
import com.desafio_spring_jpa.screen_sound.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);

    private ArtistaRepository artistaRepository;

    private AlbumRepository albumRepository;

    private MusicaRepository musicaRepository;

    public Principal(ArtistaRepository artistaRepository,
                     AlbumRepository albumRepository,
                     MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            String menu = """
                    \n=== Screen Sound ===
                    
                    1. Cadastrar Artistas
                    2. Cadastrar Álbuns
                    3. Cadastrar Músicas
                    4. Listar Artistas
                    5. Listar Álbums
                    6. Listar Músicas
                    7. Buscar Músicas por Artistas
                    8. Buscar Álbuns por Artistas
                    9. Buscar Músicas por Álbuns
                    10. Buscar dados sobre um Artista
                    
                    0. Sair
                    """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarAlbum();
                    break;
                case 3:
                    cadastrarMusica();
                    break;
                case 4:
                    listarArtistas();
                    break;
                case 5:
                    listarAlbums();
                    break;
                case 6:
                    listarMusicas();
                    break;
                case 7:
                    buscarMusicasPorArtistas();
                    break;
                case 8:
                    buscarAlbumPorArtista();
                    break;
                case 9:
                    buscarMusicasPorAlbum();
                    break;
                case 10:
                    buscarDadosDeUmArtista();
                    break;
                case 0:
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.out.println("Opção ínválida. Tente novamente!");
                    break;
            }

        }
    }

    private Artista buscarArtistaPorNome() {
        var continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do artista:");
            String nome = scanner.nextLine().trim();

            Optional<Artista> artistaEncontrado =
                    artistaRepository.findByNomeIgnoreCase(nome);

            if (artistaEncontrado.isPresent()) {
                return artistaEncontrado.get();
            }

            System.out.println("\nArtista não encontrado!\n");

            System.out.println("Deseja pesquisar novamente? (S/N)");
            continuar = scanner.nextLine();
        }

        return null;
    }

    private Album buscarAlbumPorNome() {
        var continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do álbum:");
            String nome = scanner.nextLine().trim();

            Optional<Album> albumEncontrado =
                    albumRepository.findByNomeIgnoreCase(nome);

            if (albumEncontrado.isPresent()) {
                return albumEncontrado.get();
            }

            System.out.println("\nÁlbum não encontrado!\n");

            System.out.println("Deseja pesquisar novamente? (S/N)");
            continuar = scanner.nextLine();
        }

        return null;
    }

    private <T extends Enum<T>> T lerTipo(String mensagem, Class<T> enumClass) {
        T tipo = null;

        while (tipo == null) {
            System.out.println(mensagem);

            for (T valor : enumClass.getEnumConstants()) {
                System.out.println("- " + valor);
            }

            try {
                tipo = Enum.valueOf(
                        enumClass,
                        scanner.nextLine().trim().toUpperCase()
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        return tipo;
    }

    private void cadastrarArtista() {
        var continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do artista:");
            var nome = scanner.nextLine().trim();

            TipoArtista tipo = lerTipo(
                    "Informe o tipo do Artista:",
                    TipoArtista.class);

            artistaRepository.save(new Artista(nome, tipo));

            System.out.println("\nArtista cadastrado com sucesso!\n");

            System.out.println("Deseja cadastrar outro artista? (S/N)");
            continuar = scanner.nextLine();
        }
    }

    private void cadastrarAlbum() {
        var continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            Artista artista = buscarArtistaPorNome();

            if (artista == null) {
                return;
            }

            System.out.println("Informe o nome do álbum:");
            var nome= scanner.nextLine();

            if (albumRepository.existsByNomeIgnoreCaseAndArtista(nome, artista)) {
                System.out.println("Esse álbum já está cadastrado para esse artista.");
                continue;
            }

            TipoAlbum tipo = lerTipo(
                    "Informe o tipo do álbum:",
                    TipoAlbum.class);

            Album album = new Album(nome, tipo, artista);
            artista.adicionarAlbum(album);
            albumRepository.save(album);
            System.out.println("\nÁlbum cadastrado com sucesso!\n");

            System.out.println("Deseja cadastrar outro álbum? (S/N)");
            continuar = scanner.nextLine();
        }
    }

    private void cadastrarMusica() {
        var continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            Album album = buscarAlbumPorNome();

            if (album == null) {
                return;
            }

            System.out.println("Informe o nome dessa música:");
            var titulo = scanner.nextLine().trim();

            if (musicaRepository.existsByTituloIgnoreCaseAndAlbum(titulo, album)) {
                System.out.println("Essa música já está cadastrada nesse álbum.");
                continue;
            }

            Musica musica = new Musica(titulo, album);
            album.adicionarMusica(musica);
            musicaRepository.save(musica);
            System.out.println("\nMúsica cadastrada com sucesso!\n");

            System.out.println("Deseja cadastrar outra música? (S/N)");
            continuar = scanner.nextLine();
        }
    }

    private <T> void listar(List<T> itens) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        itens.forEach(System.out::println);
    }

    private void listarArtistas() {
        listar(artistaRepository.findAll());
    }

    private void listarAlbums() {
        listar(albumRepository.findAll());
    }

    private void listarMusicas() {
        listar(musicaRepository.findAll());
    }

    private void buscarMusicasPorArtistas() {
        System.out.println("Informe o nome do artista");
        var nome = scanner.nextLine();
        List<Musica> musicas = musicaRepository.buscarMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void buscarAlbumPorArtista() {
        System.out.println("Informe o nome do artista");
        var nome = scanner.nextLine();
        List<Album> albuns = albumRepository.buscarAlbunsPorArtista(nome);
        albuns.forEach(System.out::println);
    }

    private void buscarMusicasPorAlbum(){
        System.out.println("Informe o nome do álbum");
        var nome = scanner.nextLine();
        List<Musica> musicas = musicaRepository.buscarMusicasPorAlbum(nome);
        musicas.forEach(System.out::println);
    }

    private void buscarDadosDeUmArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = scanner.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());
    }

}
