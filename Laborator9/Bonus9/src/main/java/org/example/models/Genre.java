package org.example.models;

import java.io.Serializable;
import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;
import java.util.*;

@Entity
@Table(name = "genre1")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll",
                query = "select e from Genre e order by e.id"),
        @NamedQuery(name = "Genre.findById",
                query = "select e from Genre e where e.id = :id"),
        @NamedQuery(name = "Genre.findByName",
                query = "select e from Genre e where e.name = :name"),
        @NamedQuery(name = "Genre.findId",
                query = "select count(e)+1 from Genre e"),
})
public class Genre extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    public Genre(Integer id, String name) {
//        this.name = name;
//        this.id = id;
//    }

    public Genre(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre id=" + id + ", name="+ name;
    }

//    @ManyToMany(mappedBy = "genres")
//    private Set<Album> albums = new HashSet<>();
}