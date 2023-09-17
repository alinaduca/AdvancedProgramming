package org.example.models;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

@Entity
@Table(name = "album1")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.id"),
        @NamedQuery(name = "Album.findById",
                query = "select e from Album e where e.id = :id"),
        @NamedQuery(name = "Album.findByTitle",
                query = "select e from Album e where e.title = :title"),
        @NamedQuery(name = "Album.findId",
                query = "select count(e)+1 from Album e"),
})
public class Album extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "album_genres1",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Album(String title) {
        this.title = title;
    }

    public Album(String title, int releaseYear, Set<Genre> genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = genres;
    }

    public Album(String title, int releaseYear, Artist artist, Set<Genre> genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genres = genres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Album() { }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @OneToMany
//    private List<Genre> oneToMany;
//
//    public List<Genre> getOneToMany() {
//        return oneToMany;
//    }
//
//    public void setOneToMany(List<Genre> oneToMany) {
//        this.oneToMany = oneToMany;
//    }

//    @ManyToOne
//    @JoinColumn(name = "artist_id")
//    private Artist artist;

    @Override
    public String toString() {
        String mesaj = "Album id=" + id + ", title="+ title + ", release year=" + releaseYear + ", artist=" + artist.getName() + ", genres: [ ";
        for(Genre gen : genres) {
            if(gen != null) {
                mesaj = mesaj + gen.getName() + " ";
            }
        }
        mesaj = mesaj + "]";
        return mesaj;
    }
}