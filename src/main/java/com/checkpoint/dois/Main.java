package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Album;
import org.example.models.Artist;
import org.example.models.Track;
import org.example.repositories.AlbumRepository;
import org.example.repositories.ArtistRepository;
import org.example.repositories.TrackRepository;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.
                    createEntityManagerFactory("TESTECP-PU");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            var customerRepository = new CustomerRepository(entityManager);
            var tellerRepository = new TellerRepository(entityManager);

            var novoCustomer = new Customer("Teste", "RuaTeste", 43434, 434334);
            customerRepository.create(novoCustomer);
            var customer = customerRepository.findById(1);

            novoCustomer.setName("Teste2");
            customerRepository.update(novoCustomer);
            var customerUpdate = customerRepository.findById(1);

            customerRepository.deleteById(1);
            var customerDeleted = customerRepository.findById(1);

            entityManager.close();
            entityManagerFactory.close();
        } catch (Exception e) {
            throw e;
        }
    }
}