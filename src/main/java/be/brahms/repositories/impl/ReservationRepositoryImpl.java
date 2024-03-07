package be.brahms.repositories.impl;

import be.brahms.entities.Reservation;
import be.brahms.repositories.ReservationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    // Connect to DataBase
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public void reserveBook(Reservation reservation) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(reservation);
            tx.commit();
        } finally {
            s.close();
        }
    }

    @Override
    public Reservation backBook(long id, Reservation reservation) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {

            Reservation backBook = s.get(Reservation.class, id);

            tx = s.beginTransaction();
            s.merge(reservation);
            tx.commit();
            return backBook;
        } finally {
            s.close();
        }
    }

    @Override
    public Reservation updateDeadline(long id, Reservation reservation) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {

            Reservation updateDeadline = s.get(Reservation.class, id);

            tx = s.beginTransaction();
            s.merge(reservation);
            tx.commit();
            return updateDeadline;
        } finally {
            s.close();
        }
    }

    @Override
    public Reservation findById(long id) {
        Session s = sf.openSession();
        try {
            return s.get(Reservation.class, id);
        } finally {
            s.close();
        }

    }

    @Override
    public List<Reservation> getAllBookByAuthor(String niss) {
        Session s = sf.openSession();

        try {
            String hql = "FROM Reservation r WHERE  r.isBack = false  AND r.client.niss = :niss";
            Query<Reservation> query = s.createQuery(hql, Reservation.class);
            query.setParameter("niss", niss);

            return query.getResultList();
        } finally {
            s.close();
        }

    }

    @Override
    public List<Reservation> getAllBookByTitle(String title) {
        Session s = sf.openSession();

        try {
            String hql = "FROM Reservation r WHERE  r.isBack = false  AND r.book.title LIKE :title";
            Query<Reservation> query = s.createQuery(hql, Reservation.class);
            query.setParameter("title", "%" + title + "%");

            return query.getResultList();
        } finally {
            s.close();
        }
    }

    @Override
    public List<Reservation> getAllBookByISBN(int isbn) {
        Session s = sf.openSession();

        try {
            String hql = "FROM Reservation r WHERE  r.isBack = false  AND r.book.isbn = :isbn";
            Query<Reservation> query = s.createQuery(hql, Reservation.class);
            query.setParameter("isbn", isbn);

            return query.getResultList();
        } finally {
            s.close();
        }
    }
}
