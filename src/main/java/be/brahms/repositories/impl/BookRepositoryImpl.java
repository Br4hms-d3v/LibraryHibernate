package be.brahms.repositories.impl;

import be.brahms.entities.Book;
import be.brahms.repositories.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    //Connect to Database
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public void create(Book book) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(book);
            tx.commit();
        } finally {
            s.close();
        }
    }

    @Override
    public Book update(long id, Book book) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getBooksByTitle() {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor() {
        return null;
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        return null;
    }

    @Override
    public boolean existingIsbn(int isbn) {
        Session s = sf.openSession();

        try {
            String hql = "SELECT COUNT (*) FROM Book b WHERE b.isbn = :isbn";
            Query<Long> query = s.createQuery(hql, Long.class);
            query.setParameter("isbn", isbn);

            Long count = query.uniqueResult();
            return count != null && count >0;
        } finally {
            s.close();
        }

    }
}
