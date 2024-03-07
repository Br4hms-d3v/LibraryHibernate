package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Book;
import be.brahms.entities.Client;
import be.brahms.entities.Reservation;
import be.brahms.services.ReservationServiceImpl;

import java.util.Scanner;

public class ReservationManager {

    /**
     *
     * MAIN MENU FOR RESERVATION
     *
     */
    public static void reservations() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Louer d'un livre\s
                    2- Retour d'un livre\s
                    3- Modifier le délais\s
                    4- Liste des livres loué via le client\s
                    5- Liste des clients qui loue le livre\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Location d'un livre \n " );
                    borrowBook();
                }
                case 2 -> {
                    System.out.println(" \n Le retour du livre \n " );
                    backBook();
                }
                case 3 -> {
                    System.out.println(" \n Modification du délais d'un livre \n " );
                    updateDeadline();
                }
                case 4 -> {
                    System.out.println(" \n Voir la liste des livres loué par le client \n " );

                }
                case 5 -> {
                    System.out.println(" \n Voir la liste des clients qui ont loué via isbn \n " );

                }
                case 0 -> {
                    System.out.println(" Vous serez rediriger vers le menu principal");
                    stopMethode = true ;
                    Main.appLibrary();
                }
                default -> System.out.println(" Veuillez choisir le numéro 1, 2, 3 ou 4" );
            }

        } while( !stopMethode );

    }

    /**
     *
     * BORROW A BOOK
     *
     */
    private static void borrowBook() {

        // Call reservation Service
        ReservationServiceImpl reservationService = new ReservationServiceImpl();

        // Declare variables
        Reservation newReservation = new Reservation();
        Client client = new Client();
        Book book = new Book();

        Scanner scan = new Scanner(System.in);
        String niss;
        int isbn;

        System.out.println( "Entrez le Niss du client" );
        niss = scan.nextLine();

        System.out.println( "Entrez le numéro ISBN du livre " );
        isbn = Integer.parseInt(scan.nextLine());

        client.setNiss(niss);
        book.setIsbn(isbn);

        newReservation.setClient(client);
        newReservation.setBook(book);

        reservationService.reservation(newReservation);

    }

    /**
     *
     * BACK THE BOOK
     *
     */
    private static void backBook() {
        // Call reservation Service
        ReservationServiceImpl reservationService = new ReservationServiceImpl();

        // Declare variables
        Reservation newReservation = new Reservation();
        Client client = new Client();
        Book book = new Book();

        Scanner scan = new Scanner(System.in);
        String niss;
        int isbn;
        long idReservation;

        System.out.println( "Entrez l'ID de la reservation" );
        idReservation = Long.parseLong(scan.nextLine());

        System.out.println( "Entrez le Niss du client" );
        niss = scan.nextLine();

        System.out.println( "Entrez le numéro ISBN du livre " );
        isbn = Integer.parseInt(scan.nextLine());

        client.setNiss(niss);
        book.setIsbn(isbn);

        newReservation.setClient(client);
        newReservation.setBook(book);

        reservationService.backBook(idReservation, newReservation);
    }

    /**
     *
     * UPDATE DEADLINE FOR BOOK
     *
     */
    private static void updateDeadline() {
        // Call reservation Service
        ReservationServiceImpl reservationService = new ReservationServiceImpl();

        // Declare variables
        Reservation newReservation = new Reservation();
        Client client = new Client();
        Book book = new Book();

        Scanner scan = new Scanner(System.in);
        String niss;
        int isbn;
        long idReservation;

        System.out.println( "Entrez l'ID de la reservation" );
        idReservation = Long.parseLong(scan.nextLine());

        System.out.println( "Entrez le Niss du client" );
        niss = scan.nextLine();

        System.out.println( "Entrez le numéro ISBN du livre " );
        isbn = Integer.parseInt(scan.nextLine());

        client.setNiss(niss);
        book.setIsbn(isbn);

        newReservation.setClient(client);
        newReservation.setBook(book);

        reservationService.updateDeadlineBook(idReservation, newReservation);
    }

}
