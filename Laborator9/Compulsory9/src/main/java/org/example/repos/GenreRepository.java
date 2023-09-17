package org.example.repos;

import jakarta.persistence.EntityManager;
import org.example.models.Genre;
import org.example.utils.EntityManagerFactorySingleton;
import java.util.List;

public class GenreRepository extends DataRepository<Genre, Integer> {
    public GenreRepository() {
        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.getEntityManager());
    }

    public Genre findById(Long id) {
        return (Genre) getEntityManager().createNamedQuery("Genre.findById").setParameter("id", id).getSingleResult();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Genre.findId").getSingleResult();
//        return 5l;
    }

    public List<Genre> findByName(String name) {
        return (List<Genre>) getEntityManager().createNamedQuery("Genre.findByName").setParameter("name", name).getResultList();
    }

    public void create(EntityManager entityManager, Genre genre){
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }
}
