package org.example.models;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;
import java.util.*;
import java.io.Serializable;

@Entity
@Table(name = "artist1")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll",
                query = "select e from Artist e order by e.id"),
        @NamedQuery(name = "Artist.findById",
                query = "select e from Artist e where e.id = :id"),
        @NamedQuery(name = "Artist.findByName",
                query = "select e from Artist e where e.name = :name"),
        @NamedQuery(name = "Artist.findId",
                query = "select count(e)+1 from Artist e"),
})
public class Artist extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist() {
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

//    @OneToMany(mappedBy = "artist")
//    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "artist")//, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums = new ArrayList<>();

    @Override
    public String toString() {
        return "Artist id=" + id + ", name="+ name;
    }
}
