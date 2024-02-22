package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Client;
import be.brahms.services.ClientServiceImpl;

import java.util.Scanner;

public class ClientManager {

    public static void clients() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;

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
                    createClient();
                }
                case 2 -> {
                    System.out.println(" \n Modification du client \n " );
                    updateClient();
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


    // Create a new client
    private static void createClient() {

        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();

        //Declare variable
        Scanner scan = new Scanner(System.in);
        String name, firstname, niss, email, phonenumber;

        System.out.println("Nom");
        name = scan.nextLine();

        System.out.println("prenom");
        firstname = scan.nextLine();

        System.out.println("niss");
        niss = scan.nextLine();

        System.out.println("email");
        email = scan.nextLine();

        System.out.println("num gsm");
        phonenumber = scan.nextLine();

        Client newClient = new Client();
        newClient.setName(name);
        newClient.setFirstname(firstname);
        newClient.setNiss(niss);
        newClient.setEmail(email);
        newClient.setPhoneNumber(phonenumber);

        clientService.create(newClient);
    }

<<<<<<< HEAD
    // Update a client
=======
>>>>>>> dev
    private static void updateClient() {

        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();

<<<<<<< HEAD
        //Declare variable
        Scanner scan = new Scanner(System.in);
        int choice, idClient;
        boolean stopMethode = false;
        String name, firstname, niss, email, phonenumber;
        Client updateClient = new Client();

        System.out.println("Entrez l'ID du client");
        idClient = Integer.parseInt( scan.nextLine() );;
=======
        // Declare variables
        Scanner scan = new Scanner(System.in);
        Client updateClient = new Client();
        int choice, idClient;
        boolean stopMethode = false;
        String name, firstname, niss, email, phonenumber;

        System.out.println("Veuillez entrez l'ID du client");
        idClient = Integer.parseInt(scan.nextLine());
>>>>>>> dev

        do {

            // This is the main for my application
            System.out.println("""
<<<<<<< HEAD
                     Que souhaitez-vous modifier: \s
=======
                     Que voulez-vous modifier ?\s
>>>>>>> dev
                    1- Nom\s
                    2- Prénom\s
                    3- Niss\s
                    4- Email\s
<<<<<<< HEAD
                    5- Numéro de GSM\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Entrez le nom de famille: " );
                    name = scan.next();
=======
                    5- Numéro GSM\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Entrez le nom de famille:" );
                    name = scan.nextLine();
>>>>>>> dev
                    updateClient.setName(name);
                    clientService.update(idClient, updateClient);
                }
                case 2 -> {
<<<<<<< HEAD
                    System.out.println(" \n Entrez le prénom: " );
=======
                    System.out.println(" \n Entrez le prénom:" );
>>>>>>> dev
                    firstname = scan.nextLine();
                    updateClient.setFirstname(firstname);
                    clientService.update(idClient, updateClient);
                }
                case 3 -> {
<<<<<<< HEAD
                    System.out.println(" \n Entrez le niss: " );
=======
                    System.out.println(" \n Entrez le niss:" );
>>>>>>> dev
                    niss = scan.nextLine();
                    updateClient.setNiss(niss);
                    clientService.update(idClient, updateClient);
                }
                case 4 -> {
<<<<<<< HEAD
                    System.out.println(" \n Entrez l'adresse email: " );
=======
                    System.out.println(" \n Entrez l'adresse email:" );
>>>>>>> dev
                    email = scan.nextLine();
                    updateClient.setEmail(email);
                    clientService.update(idClient, updateClient);
                }
                case 5 -> {
<<<<<<< HEAD
                    System.out.println(" \n Entrez le numéro de GSM: " );
=======
                    System.out.println(" \n Entrez le numéro de GSM:" );
>>>>>>> dev
                    phonenumber = scan.nextLine();
                    updateClient.setPhoneNumber(phonenumber);
                    clientService.update(idClient, updateClient);
                }
                case 0 -> {
                    System.out.println(" Vous serez rediriger vers le menu principal");
                    stopMethode = true ;
                    clients();
                }
                default -> System.out.println(" Veuillez choisir le numéro 1, 2, 3, 4, 5 ou 0" );
            }

        } while( !stopMethode );
<<<<<<< HEAD
    }

=======

    }
>>>>>>> dev

}
