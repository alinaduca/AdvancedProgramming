package org.example.repos;

import jakarta.persistence.EntityManager;
import org.example.models.*;
import org.example.utils.EntityManagerFactorySingleton;

import java.util.List;

public class ArtistRepository extends DataRepository<Artist, Integer> {
    public ArtistRepository() {
        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.getEntityManager());
    }

    public Artist findById(Long id) {
        return (Artist) getEntityManager().createNamedQuery("Artist.findById").setParameter("id", id).getSingleResult();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Artist.findId").getSingleResult();
    }

    public List<Artist> findByName(String name) {
        return (List<Artist>) getEntityManager().createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
    }

    public void create(EntityManager entityManager, Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
//        entityManager.close();
    }
}
