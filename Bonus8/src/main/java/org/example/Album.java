package org.example;

import java.util.*;

public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private Artist artist;
    private List<Genre> genres;

    public Album(int id, int releaseYear, String title, Artist artist, List<Genre> genres) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album ").append(title).append(", by artist ").append(artist.getName()).append(", with releaseYear ").append(releaseYear).append(" and genres: ");
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
