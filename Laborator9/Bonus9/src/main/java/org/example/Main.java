package org.example;

import org.example.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.repos.*;
import org.example.utils.EntityManagerFactorySingleton;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        // Generate and insert 1000 artists with random names
//        long startTime = System.nanoTime();
        ArtistRepository artistRepository = new ArtistRepository();
//        for(int i = 0; i < 1000; i++) {
//            Artist artist = new Artist("Artist" + i);
//            artist.setId(artistRepository.findId());
//            artistRepository.create(entityManager, artist);
//            System.out.println(artist);
//        }
//
//        // Generate and insert 1000 genres with random names
        GenreRepository genreRepository = new GenreRepository();
//        for(int i = 0; i < 1000; i++) {
//            Genre genre = new Genre("Genre" + i);
//            genre.setId(genreRepository.findId());
//            genreRepository.create(entityManager, genre);
//            System.out.println(genre);
//        }

        // Generate and insert 1000 artists with random names
        AlbumRepository albumRepository = new AlbumRepository();
        for(int i = 0; i < 1000; i++) {
            Set<Genre> genreSet = new HashSet<>();
            genreSet.add(genreRepository.findById((long)(i % 500 + 1)));
            genreSet.add(genreRepository.findById((long)(i % 500 + 500)));
            Album album = new Album("Album" + i, 1950 + i % 70, artistRepository.findById((long)((i % 1000) + 1)), genreSet);
            album.setId((long) i + 1);
            albumRepository.create(album);
            System.out.println(album);
        }
        long endTime = System.nanoTime();
//        System.out.println("Execution time of the JPQL statements: " + (endTime - startTime) / 1000000 + " seconds.");

        entityManager.close();
        entityManagerFactory.close();
    }
}
