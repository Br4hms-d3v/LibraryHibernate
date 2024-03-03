package be.brahms.repositories.impl;

import be.brahms.entities.Author;
import be.brahms.repositories.AuthorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    //Connect to DataBase
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    @Override
    public void create(Author author) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(author);
            tx.commit();
        } finally {
            s.close();
        }
    }

    @Override
    public Author update(long id, Author author) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public List<Author> getByName() {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public boolean isAuthorExists(String name, String firstname) {
        Session s = sf.openSession();

        try {
            String hql = "SELECT COUNT(*)FROM Author a WHERE a.name =:name AND a.firstname =:firstname";
            Query<Long> query = s.createQuery(hql, Long.class);
            query.setParameter("name", name);
            query.setParameter("firstname", firstname);

            Long count = query.uniqueResult();
            return count !=null && count > 0;
        } finally {
            s.close();
        }

    }
}
