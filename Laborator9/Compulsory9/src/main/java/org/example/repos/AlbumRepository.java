package org.example.repos;

import org.example.models.*;
import org.example.utils.EntityManagerFactorySingleton;

import java.util.List;

public class AlbumRepository extends DataRepository<Album, Integer> {
    public AlbumRepository() {
        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.getEntityManager());
    }

    public Artist findById(Long id) {
        return (Artist) getEntityManager().createNamedQuery("Album.findById").setParameter("id", id).getSingleResult();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Album.findId").getSingleResult();
    }

    public List<Album> findByName(String name) {
        return (List<Album>) getEntityManager().createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
    }

    public void create(Album album) {
        beginTransaction();
        getEntityManager().persist(album);
        commit();
    }
}
