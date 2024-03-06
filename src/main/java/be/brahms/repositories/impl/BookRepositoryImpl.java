package be.brahms.repositories.impl;

import be.brahms.entities.Author;
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
    public Book update(int isbn, Book book) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            // Chargement d'un livre en utilisant l'identifiant naturel (ISBN)
            Book updateBook = s.byNaturalId(Book.class)
                    .using("isbn", isbn) // Spécifie la propriété et sa valeur pour l'identifiant naturel
                    .load(); // Charge le livre en fonction de l'identifiant naturel fourni

            if( book.getTitle() != null ) {
                updateBook.setTitle(book.getTitle());
            } if ( book.getQtyBooks() != 0 ) {
                updateBook.setQtyBooks(book.getQtyBooks());
            } if ( book.getNbPages() != 0 ) {
                updateBook.setNbPages(book.getNbPages());
            } if ( book.getAuthor() != null && book.getAuthor().getId() != 0) {
                // Create a new Object for AuthorID
                Author newAuthor = new Author();
                newAuthor.setId(book.getAuthor().getId());
                // Implement this new Author for this book
                updateBook.setAuthor(newAuthor);
            }

            tx = s.beginTransaction();
            s.merge(updateBook);
            tx.commit();
            return updateBook;
        } finally {
            s.close();
        }

    }

    @Override
    public void delete(int isbn) {
        Session s = sf.openSession();
        Transaction tx = null;
        Book deleteBook = s.byNaturalId(Book.class)
                .using("isbn", isbn) // Spécifie la propriété et sa valeur pour l'identifiant naturel
                .load(); // Charge le livre en fonction de l'identifiant naturel fourni

        try {
            if (deleteBook != null) {
                tx = s.beginTransaction();
                s.remove(deleteBook);
                tx.commit();
            }
        } finally {
            s.close();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        Session s = sf.openSession();

        try {
            String hql = "FROM Book";
            Query<Book> query = s.createQuery(hql, Book.class);
            return query.getResultList();
        } finally {
            s.close();
        }

    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        Session s = sf.openSession();

        try {
            String hql = "SELECT b FROM Book b WHERE b.title LIKE :title";
            Query<Book> query = s.createQuery(hql, Book.class);
            query.setParameter("title", "%" + title + "%");
            return query.getResultList();
        } finally {
            s.close();
        }

    }

    @Override
    public List<Book> getBooksByAuthor(String name) {
        return null;
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        Session s = sf.openSession();
        try {
            String hql = "FROM Book b WHERE b.isbn = :isbn";
            Query query = s.createQuery(hql, Book.class);
            query.setParameter("isbn", isbn);
            return (Book) query.uniqueResult();
        } finally {
            s.close();
        }
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
