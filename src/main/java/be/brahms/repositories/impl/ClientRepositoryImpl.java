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

    //Connect to DataBase
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * @return All clients
     */
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
    public List<Client> getByName(String name) {
        Session s = sf.openSession();
        try {
            String hql = "FROM Client c WHERE c.name = :name ";
            Query<Client> query = s.createQuery(hql, Client.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            s.close();
        }
    }

    /**
     * @return a new client
     */
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

    /**
     * @return a update client
     */
    @Override
    public Client update(long id, Client client) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            Client updateClient = s.get(Client.class, id);

            if (updateClient != null) {
                // Mettez à jour uniquement le champ spécifique
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
        Session s = sf.openSession();
        Transaction tx = null;
        Client clientToDelete = s.get(Client.class, id);

        try {
            if( clientToDelete != null ){
                tx = s.beginTransaction();
                s.remove(clientToDelete);
                tx.commit();
            }
        } finally {
            s.close();
        }
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
