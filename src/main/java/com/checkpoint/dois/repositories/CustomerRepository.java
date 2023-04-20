package com.checkpoint.dois.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.models.Customer;

import java.util.List;

public class CustomerRepository {

    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Iterable<Customer> read() {
        String jpql = "SELECT c FROM Customer c";
        var query = entityManager.createQuery(jpql, Customer.class)
                .setHint("jakarta.persistence.query.timeout", 60000);
        var customers = query.getResultList();
        return customers;
    }

    public void update(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Customer customer) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Customer findById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer == null) {
            return null;
        }
        return customer;
    }

    public void deleteById(int id) {
        entityManager.getTransaction().begin();
        try {
            Customer customer = entityManager.find(Customer.class, id);
            if (customer != null) {
                entityManager.remove(customer);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
