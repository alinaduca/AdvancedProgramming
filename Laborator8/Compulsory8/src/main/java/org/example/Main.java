package org.example;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        AlbumDAO albumDAO = new AlbumDAO();
        Connection connection = null;
        try {
            connection = Database.getConnection();

            ArtistDAO artistDAO = new ArtistDAO(connection);
            System.out.println("Artist by ID:");
            System.out.println(artistDAO.getArtistById(1));
            System.out.println();

//            Artist artist = new Artist(4, "Abba");
//            artistDAO.addArtist(artist);

            System.out.println("All artists:");
            System.out.println(artistDAO.getAllArtists());
            System.out.println();

            GenreDAO genreDAO = new GenreDAO(connection);
            System.out.println("Genre by ID:");
            System.out.println(genreDAO.getGenreById(2));
            System.out.println();

//            Genre genre = new Genre(4, "Hip Hop");
//            genreDAO.addGenre(genre);

            System.out.println("All genres:");
            System.out.println(genreDAO.getAllGenres());
            System.out.println();

            System.out.println("All albums:");
            System.out.println(albumDAO.getAllAlbums());
            System.out.println();

//            Album album = new Album(4, 1967, "New Album", artistDAO.getArtistById(2), Arrays.asList(genreDAO.getGenreById(3)));
//            albumDAO.addAlbum(album);

            Album albumById = albumDAO.getAlbumById(1);
            System.out.println("Album by id:");
            System.out.println(albumById.toString());
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
