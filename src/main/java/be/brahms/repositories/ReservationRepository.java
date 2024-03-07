package be.brahms.repositories;

import be.brahms.entities.Reservation;

public interface ReservationRepository {

    void reserveBook(Reservation reservation);
    Reservation backBook(long id, Reservation reservation);
    Reservation updateDeadline(long id, Reservation reservation);
    Reservation findById(long id);
}
