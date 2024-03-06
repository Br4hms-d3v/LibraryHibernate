package be.brahms.repositories.impl;

import be.brahms.entities.Reservation;
import be.brahms.repositories.ReservationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
}
