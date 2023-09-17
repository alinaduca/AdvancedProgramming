package org.example;

import org.example.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.repos.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        GenreRepository genreRepository = new GenreRepository();
        Genre genre = new Genre("rock");
        genre.setId(genreRepository.findId());
        for(Genre gen : genreRepository.findByName("pop")) {
            System.out.println(gen);
        }
        System.out.println("Genul cu id-ul 7: " + genreRepository.findById(7l));
        genreRepository.create(entityManager, genre);

        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist("The beatles");
        artist.setId(artistRepository.findId());
        for(Artist art : artistRepository.findByName("The beatles")) {
            System.out.println(art);
        }
        artistRepository.create(entityManager, artist);



        entityManager.close();
        entityManagerFactory.close();
    }
}