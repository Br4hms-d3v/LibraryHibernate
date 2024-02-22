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
<<<<<<< HEAD

=======
>>>>>>> dev
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            Client updateClient = s.get(Client.class, id);

<<<<<<< HEAD
            if( updateClient != null ) {
=======
            if (updateClient != null) {
                // Mettez à jour uniquement le champ spécifique
>>>>>>> dev
                if (client.getName() != null) {
                    updateClient.setName(client.getName());
                }
                if (client.getFirstname() != null) {
                    updateClient.setFirstname(client.getFirstname());
                }
                if (client.getNiss() != null) {
                    updateClient.setNiss(client.getNiss());
                }
                if (client.getEmail() != null) {
                    updateClient.setEmail(client.getEmail());
                }
                if (client.getPhoneNumber() != null) {
                    updateClient.setPhoneNumber(client.getPhoneNumber());
                }

                tx = s.beginTransaction();
                s.merge(updateClient);
                tx.commit();
                return updateClient;
            }
        } finally {
            s.close();
        }
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Client findById(long id) {
        Session s = sf.openSession();

        try {
            return s.get(Client.class, id);
        } finally {
            s.close();
        }
    }
}
