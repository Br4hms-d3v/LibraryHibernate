package be.brahms.services;

import be.brahms.entities.Book;
import be.brahms.entities.Client;
import be.brahms.entities.Reservation;
import be.brahms.repositories.BookRepository;
import be.brahms.repositories.ClientRepository;
import be.brahms.repositories.ReservationRepository;
import be.brahms.repositories.impl.BookRepositoryImpl;
import be.brahms.repositories.impl.ClientRepositoryImpl;
import be.brahms.repositories.impl.ReservationRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class ReservationServiceImpl {

    // Call repositories

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    // Constructor
    public ReservationServiceImpl() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.clientRepository = new ClientRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
    }


    public void reservation(Reservation reservation) {

        Client clientExisting = clientRepository.getByNiss(reservation.getClient().getNiss());
        Book bookExisting = bookRepository.getBookByIsbn(reservation.getBook().getIsbn());

        LocalDate startDate, endDate;
        startDate = LocalDate.now();
        endDate = startDate.plusDays(14);

        if(clientExisting != null && bookExisting != null) {

           // Create a reservation for a book
           reservation.getClient().setId(clientExisting.getId());
           reservation.getBook().setId(bookExisting.getId());
           reservation.setStartBorrow(startDate);
           reservation.setEndBorrow(endDate);
           reservation.setBack(false);

           if( bookExisting.getQtyBooks() >1) {
               reservationRepository.reserveBook(reservation);

               // This is updated Quantity book
               bookExisting.setQtyBooks(bookExisting.getQtyBooks() - 1);
               bookRepository.update(bookExisting.getIsbn(), bookExisting);
               System.out.println(" Vous avec bien réservé le livre pour le client ");
           } else {
               System.out.println(" Malheureusement nous n'avons plus de stock");
           }

        } else {
            System.out.println( " Le numéro ISBN ou le NISS du client n'existe pas");
        }

    }

    // Client hands over the book
    public void backBook(long id, Reservation reservation) {

        Client clientExisting = clientRepository.getByNiss(reservation.getClient().getNiss());
        Book bookExisting = bookRepository.getBookByIsbn(reservation.getBook().getIsbn());
        Reservation idReservation = reservationRepository.findById(id);

        LocalDate dateBack = LocalDate.now();

        if (clientExisting != null && bookExisting != null && idReservation != null) {
            // Create a reservation for a book
            idReservation.getClient().setId(clientExisting.getId());
            idReservation.getBook().setId(bookExisting.getId());
            idReservation.setDateBack(dateBack);
            idReservation.setBack(true);

            // Try to back the book in the repository
            reservationRepository.backBook(id, idReservation);

            // This is updated Quantity book
            bookExisting.setQtyBooks(bookExisting.getQtyBooks() + 1);
            bookRepository.update(bookExisting.getIsbn(), bookExisting);

        } else {
            System.out.println("Le numéro ISBN, le NISS du client ou l'id Réservation n'existe pas");
        }

    }

    // Client add the deadline for the book
    public void updateDeadlineBook( long id, Reservation reservation) {
        Client clientExiste = clientRepository.getByNiss(reservation.getClient().getNiss());
        Book bookExiste = bookRepository.getBookByIsbn(reservation.getBook().getIsbn());
        Reservation idReservation = reservationRepository.findById(id);

        LocalDate endBorrow = idReservation.getEndBorrow();

        if (clientExiste != null && bookExiste != null && idReservation != null) {
            // Create a reservation for a book
            idReservation.getClient().setId(clientExiste.getId());
            idReservation.getBook().setId(bookExiste.getId());
            idReservation.setEndBorrow(endBorrow.plusDays(7));
            idReservation.setBack(false);

            // Try to back the book in the repository
            reservationRepository.backBook(id, idReservation);

        } else {
            System.out.println("Le numéro ISBN, le NISS du client ou l'id Réservation n'existe pas");
        }
    }

    // List client who borrow a book
    public List<Reservation> listBookBorrowByClient(String niss ) {
        return reservationRepository.getAllBookByAuthor(niss);
    }

}
