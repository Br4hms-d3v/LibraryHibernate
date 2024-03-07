package be.brahms.repositories;

import be.brahms.entities.Reservation;

import java.util.List;

public interface ReservationRepository {

    void reserveBook(Reservation reservation);
    Reservation backBook(long id, Reservation reservation);
    Reservation updateDeadline(long id, Reservation reservation);
    Reservation findById(long id);
    List<Reservation> getAllBookByAuthor( String niss);
    List<Reservation> getAllBookByTitle( String title);
    List<Reservation> getAllBookByISBN(int isbn);
}
