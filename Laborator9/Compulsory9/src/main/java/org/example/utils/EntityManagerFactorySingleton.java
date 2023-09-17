package org.example.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManagerFactorySingleton entityManagerFactorySingleton = null;
    private EntityManagerFactorySingleton() { }

    public static EntityManagerFactorySingleton getInstance() {
        if(entityManagerFactorySingleton == null) {
            entityManagerFactorySingleton = new EntityManagerFactorySingleton();
            entityManagerFactory = Persistence.createEntityManagerFactory("ExamplePU");
        }
        return entityManagerFactorySingleton;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    
    public static void closeEntity() {
        entityManagerFactory.close();
    }
}