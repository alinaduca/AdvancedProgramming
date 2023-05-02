package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RollingStoneAlbumImporter {
    private final ArtistDAO artistDao;
    private final GenreDAO genreDao;
    private final AlbumDAO albumDao;

    public RollingStoneAlbumImporter(ArtistDAO artistDao, GenreDAO genreDao, AlbumDAO albumDao) {
        this.artistDao = artistDao;
        this.genreDao = genreDao;
        this.albumDao = albumDao;
    }

    public void importAlbums(String filename) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String artistName = parts[3];
                artistName = artistName.replace('\'', ' ');
                System.out.println( i + ".) " + line);
                i++;
                if(!parts[1].equals("Year")) {
                    String albumTitle = parts[2];
                    albumTitle = albumTitle.replace('\'', ' ');
                    int year = Integer.parseInt(parts[1]);
                    String[] genres = parts[4].split("/");

                    // Check if artist already exists in the database
                    Artist artist = artistDao.findByName(artistName);
                    if (artist == null) {
                        artist = new Artist(artistDao.getNewId(), artistName);
                        artistDao.addArtist(artist);
                    }

                    // Check if genres already exist in the database
                    List<Genre> genreList = new ArrayList<>();
                    for (String genreName : genres) {
                        Genre genre = genreDao.findByName(genreName);
                        if (genre == null) {
                            genre = new Genre(genreDao.getNewId(), genreName);
                            genreDao.addGenre(genre);
                        }
                        genreList.add(genre);
                    }

                    // Create the album and link it to the artist and genres
                    Album album = new Album(albumDao.getNewId(), year, albumTitle, artist, genreList);
                    albumDao.addAlbum(album);
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
