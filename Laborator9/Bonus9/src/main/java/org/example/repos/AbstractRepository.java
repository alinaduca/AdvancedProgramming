package org.example.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.dom4j.tree.AbstractEntity;
import org.example.utils.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity, ID extends Serializable> {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T findById(ID id) {
        return null;
    }
    public List<T> findByName(ID id) {
        return null;
    }

     public boolean persist(T entity) {
        try {
            beginTransaction();
            EntityManagerFactory entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
            EntityManager entityManager = entityManagerFactorySingleton.createEntityManager();
            entityManager.persist(entity);
            commit();
            return true;
        } catch (Exception e) {
            handleException(e);
            rollback();
        }
        return false;
    }
    private void rollback() {
        entityManager.getTransaction().rollback();
    }

    private void handleException(Exception exception) {
        System.out.println(exception);
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }
}