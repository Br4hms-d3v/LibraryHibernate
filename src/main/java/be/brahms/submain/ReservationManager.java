package be.brahms.submain;

import be.brahms.Main;

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

                }
                case 2 -> {
                    System.out.println(" \n Le retour du livre \n " );

                }
                case 3 -> {
                    System.out.println(" \n Modification du délais d'un livre \n " );

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

}
