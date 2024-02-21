package be.brahms.repositories.impl;

import be.brahms.entities.Client;
import be.brahms.repositories.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public List<Client> getAll() {
        try (Session session = sf.openSession()) {
            String hql = "FROM Client";
            Query<Client> query = session.createQuery(hql, Client.class);
            return query.getResultList();
        }
    }

    @Override
    public Client getByNiss(String niss) {
        return null;
    }

    @Override
    public Client getByName(String name) {
        return null;
    }

    @Override
    public void create(Client client) {

        Session s = sf.openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(client);
            tx.commit();
        } finally {
            s.close();
        }

    }

    @Override
    public Client update(long id, Client client) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
