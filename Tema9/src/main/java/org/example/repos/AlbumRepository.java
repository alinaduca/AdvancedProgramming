package org.example.repos;

import jakarta.persistence.EntityExistsException;
import org.example.models.*;
import jakarta.persistence.EntityManagerFactory;
import org.example.utils.EntityManagerFactorySingleton;

import java.util.List;

public class AlbumRepository extends AbstractRepository<Album, Integer> {
    public AlbumRepository() {
        EntityManagerFactory entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.createEntityManager());
    }

    public Album findById(Long id) {
        return (Album) getEntityManager().createNamedQuery("Album.findById").setParameter("id", id).getSingleResult();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Album.findId").getSingleResult();
    }

    public List<Album> findByName(String title) {
        return (List<Album>) getEntityManager().createNamedQuery("Album.findByTitle").setParameter("title", title).getResultList();
    }

    public void create(Album album) {
        beginTransaction();
        try {
            getEntityManager().persist(album);
        } catch(EntityExistsException e) {
            System.out.println("Albumul " + album + " a fost deja introdus.");
        }

        commit();
    }
}
