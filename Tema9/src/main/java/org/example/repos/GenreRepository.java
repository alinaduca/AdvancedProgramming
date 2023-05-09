package org.example.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.example.models.Genre;
import org.example.utils.EntityManagerFactorySingleton;
import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Integer> {
    public GenreRepository() {
        EntityManagerFactory entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.createEntityManager());
    }

    public Genre findById(Long id) {
        Genre genre = null;
        try {
            genre = (Genre) getEntityManager().createNamedQuery("Genre.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nu exista niciun gen cu id-ul " + id + " inserat in baza de date.");
        } finally {
            return genre;
        }
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Genre.findId").getSingleResult();
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
