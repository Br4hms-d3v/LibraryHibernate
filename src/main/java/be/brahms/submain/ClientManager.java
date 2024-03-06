package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Client;
import be.brahms.services.ClientServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ClientManager {

    /**
     *
     * MAIN MENU FOR CLIENT
     *
     */
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
                    deleteClient();
                }
                case 4 -> {
                    System.out.println(" \n Recherche le nom du client \n " );
                    searchClientByName();
                }
                case 5 -> {
                    System.out.println(" \n Recherche du client via le niss \n " );
                    searchClientByNiss();
                }
                case 6 -> {
                    System.out.println(" \n Voir la liste des clients \n");
                    listClients();
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
     * CREATE A NEW CLIENT
     *
     */
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
        newClient.setName(name.toUpperCase());
        newClient.setFirstname(firstname.toLowerCase());
        newClient.setNiss(niss);
        newClient.setEmail(email);
        newClient.setPhoneNumber(phonenumber);

        clientService.create(newClient);
    }

    /**
     *
     * UPDATE A CLIENT
     *
     */
    private static void updateClient() {

        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();

        // Declare variables
        Scanner scan = new Scanner(System.in);
        Client updateClient = new Client();
        int choice, idClient;
        boolean stopMethode = false;
        String name, firstname, niss, email, phonenumber;

        System.out.println("Veuillez entrez l'ID du client");
        idClient = Integer.parseInt(scan.nextLine());

        do {

            // This is the main for my application
            System.out.println("""
                     Que voulez-vous modifier ?\s
                    1- Nom\s
                    2- Prénom\s
                    3- Niss\s
                    4- Email\s
                    5- Numéro GSM\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Entrez le nom de famille:" );
                    name = scan.nextLine();
                    updateClient.setName(name.toUpperCase());
                    clientService.update(idClient, updateClient);
                }
                case 2 -> {
                    System.out.println(" \n Entrez le prénom:" );
                    firstname = scan.nextLine();
                    updateClient.setFirstname(firstname.toLowerCase());
                    clientService.update(idClient, updateClient);
                }
                case 3 -> {
                    System.out.println(" \n Entrez le niss:" );
                    niss = scan.nextLine();
                    updateClient.setNiss(niss);
                    clientService.update(idClient, updateClient);
                }
                case 4 -> {
                    System.out.println(" \n Entrez l'adresse email:" );
                    email = scan.nextLine();
                    updateClient.setEmail(email);
                    clientService.update(idClient, updateClient);
                }
                case 5 -> {
                    System.out.println(" \n Entrez le numéro de GSM:" );
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

    }

    /**
     *
     * LIST CLIENTS
     *
     */
    private static void listClients() {

        ClientServiceImpl clientService = new ClientServiceImpl();
        List<Client> clients = clientService.getAllClients();

        for( Client client : clients ) {
            System.out.println(" Niss => " + client.getNiss() + " | Nom et Prénom: " + client.getName().toUpperCase() + " " + client.getFirstname().toLowerCase() +" ." +
                    " | Email: " + client.getEmail() + " | GSM: " + client.getPhoneNumber());
        }
    }

    /**
     *
     * DELETE CLIENTS
     *
     */
    private static void deleteClient() {

        // Call Service
        ClientServiceImpl clientService = new ClientServiceImpl();

        //Declare variable
        Scanner scan = new Scanner(System.in);
        int idClient;

        System.out.println("Entrez l'id du client auquel vous voulez supprimer ?");
        idClient = Integer.parseInt(scan.nextLine());

       clientService.delete(idClient);

    }

    /**
     *
     * SEARCH CLIENT BY NAME
     *
     */
    private static void searchClientByName() {
        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();
        Scanner scan = new Scanner(System.in);

        //Declare variable
        String name;

        System.out.println("Entrez le nom du client");
        name = scan.nextLine();

        //Call Service to find client with his name
        List<Client> clientList = clientService.getClientsByName(name.toUpperCase());


        for( Client client : clientList ) {
            System.out.println(" Niss => " + client.getNiss() + " | Nom et Prénom: " + client.getName().toUpperCase() + " " + client.getFirstname() +" ." +
                    " | Email: " + client.getEmail() + " | GSM: " + client.getPhoneNumber());
        }

    }

    /**
     *
     * SEARCH CLIENT BY NISS
     *
     */
    private static void searchClientByNiss() {
        //Call my service
        ClientServiceImpl clientService = new ClientServiceImpl();
        Scanner scan = new Scanner(System.in);

        //Declare variable
        String niss;

        System.out.println("Entrez le Niss du client!");
        niss = scan.nextLine();

        Client clientByNiss = clientService.getByNiss(niss);

        System.out.println(" Niss => " + clientByNiss.getNiss() +
                "\n Nom et Prénom: " + clientByNiss.getName().toUpperCase() + " " + clientByNiss.getFirstname() +" ." +
                "\n Email: " + clientByNiss.getEmail() +
                "\n GSM: " + clientByNiss.getPhoneNumber());
    }

}
