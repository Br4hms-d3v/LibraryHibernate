package be.brahms.submain;

import be.brahms.Main;
import be.brahms.services.ClientServiceImpl;

import java.util.Scanner;

public class ClientManager {

    public static void clients() {

        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;
        String name, firstname, niss, email, phonenumber;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Créer un client\s
                    2- Modifier un client\s
                    3- Supprimer un client\s
                    4- Chercher un client par son nom\s
                    5- Chercher un client par son niss\s
                    6- Voir la liste des clients\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Création d'un client \n " );
                    System.out.println("Nom");
                    System.out.println("prenom");
                    System.out.println("niss");
                    System.out.println("email");
                    System.out.println("num gsm");

                }
                case 2 -> {
                    System.out.println(" \n Modification du client \n " );

                }
                case 3 -> {
                    System.out.println(" \n Supression d'un client \n " );

                }
                case 4 -> {
                    System.out.println(" \n Recherche le nom du client \n " );

                }
                case 5 -> {
                    System.out.println(" \n Recherche du client via le niss \n " );

                }
                case 6 -> {
                    System.out.println(" \n Voir la liste des clients \n");
                    System.out.println(clientService.getAllClients());
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
