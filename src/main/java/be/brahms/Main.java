package be.brahms;

import be.brahms.submain.AuthorManager;
import be.brahms.submain.BookManager;
import be.brahms.submain.ClientManager;
import be.brahms.submain.ReservationManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        appLibrary();

    }

    /**
     * This app start here
     * You can choose different steps
     * there are 4
     * Client - Author - Book - Reservation
     */
    public static void appLibrary() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopAppLibrary = false;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Clients\s
                    2- Auteurs\s
                    3- Livres\s
                    4- Reservations\s
                    0- Quitter le programme""");
            choice = Integer.parseInt( scan.nextLine() );

            switch (choice) {
                case 1 -> {
                    System.out.println(" Vous avez sélectionnez Client:" );
                    ClientManager.clients();
                }
                case 2 -> {
                    System.out.println(" Vous avez sélectionnez Autheurs:" );
                    AuthorManager.authors();
                }
                case 3 -> {
                    System.out.println(" Vous avez sélectionnez Livres:" );
                    BookManager.books();
                }
                case 4 -> {
                    System.out.println(" Vous avez sélectionnez Réservations:" );
                    ReservationManager.reservations();
                }
                case 0 -> {
                    System.out.println(" Merci et à bientôt !" );
                    stopAppLibrary = true ;
                }
                default -> System.out.println(" Veuillez choisir le numéro 1, 2, 3, 4 ou le 0 pour quitter" );
            }

        } while( !stopAppLibrary );

    }

}