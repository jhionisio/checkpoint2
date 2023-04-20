package com.checkpoint.dois.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.models.Teller;

import java.util.List;

public class TellerRepository {

    private EntityManager entityManager;

    public TellerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Teller teller) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(teller);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Iterable<Teller> read() {
        String jpql = "SELECT t FROM Teller t";
        var query = entityManager.createQuery(jpql, Teller.class)
                .setHint("jakarta.persistence.query.timeout", 60000);
        var Tellers = query.getResultList();
        return Tellers;
    }

    public void update(Teller teller) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(teller);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Teller teller) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(teller);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Teller findById(int id) {
        Teller teller = entityManager.find(Teller.class, id);
        if (teller == null) {
            return null;
        }
        return teller;
    }

    public void deleteById(int id) {
        entityManager.getTransaction().begin();
        try {
            Teller teller = entityManager.find(Teller.class, id);
            if (teller != null) {
                entityManager.remove(teller);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
