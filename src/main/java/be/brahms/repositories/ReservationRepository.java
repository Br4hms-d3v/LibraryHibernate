package be.brahms.repositories;

import be.brahms.entities.Reservation;

public interface ReservationRepository {

    void reserveBook(Reservation reservation);
}
