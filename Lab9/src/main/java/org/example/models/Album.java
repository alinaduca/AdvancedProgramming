package org.example.models;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;

@Entity
@Table(name = "album1")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.id"),
        @NamedQuery(name = "Album.findById",
                query = "select e from Album e where e.id = :id"),
        @NamedQuery(name = "Album.findByName",
                query = "select e from Album e where e.name = :name"),
        @NamedQuery(name = "Album.findId",
                query = "select count(e)+1 from Album e"),
})
public class Album extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Album(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album() {
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
}