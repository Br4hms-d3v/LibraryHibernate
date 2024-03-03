package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Author;
import be.brahms.services.AuthorServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AuthorManager {

    public static void authors() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Créer un auteur\s
                    2- Modifier un auteur\s
                    3- Supprimer un auteur\s
                    4- Chercher un auteur par son nom\s
                    5- Voir la liste des auteur\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch (choice) {
                case 1 -> {
                    System.out.println(" \n Création d'un client \n " );
                    createAuthor();
                }
                case 2 -> {
                    System.out.println(" \n Modification du client \n " );
                    updateAuthor();
                }
                case 3 -> {
                    System.out.println(" \n Supression d'un client \n " );
                    //deleteAuthor();
                }
                case 4 -> {
                    System.out.println(" \n Recherche le nom du client \n " );
                    //searchAuthorByName();
                }
                case 5 -> {
                    System.out.println(" \n Recherche du client via le niss \n " );
                    listAuthor();
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
    private static void createAuthor() {

        // Call author service
        AuthorServiceImpl authorService = new AuthorServiceImpl();

        // Declare variables
        Scanner scan = new Scanner(System.in);
        Author newAuthor = new Author();
        String name, firstname;

        System.out.println(" Entrez le nom de l'auteur");
        name = scan.nextLine();

        System.out.println(" Entrez le prénom de l'auteur");
        firstname = scan.nextLine();

        // Taka variable and persit to DataBase
        newAuthor.setName(name);
        newAuthor.setFirstname(firstname);
        authorService.create(newAuthor);

    }

    /**
     *
     * UPDATE A AUTHOR
     *
     */
    private static void updateAuthor(){

        //Call my Service Author
        AuthorServiceImpl authorService = new AuthorServiceImpl();

        //Declare variables
        Scanner scan = new Scanner(System.in);
        Author updateAuthor = new Author();
        int idAuthor, choice;
        boolean stopMethode = false;
        String name, firstname;

        System.out.println( "Veuillez entrez l'ID de l'auteur" );
        idAuthor = Integer.parseInt(scan.nextLine());

        do {

            System.out.println("""
                    Que voulez-vous modifier ?\s
                    1- Nom\s
                    2- Prénom\s
                    0- Pour revenir en arrière\s                    
                    """);
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println(" Entrez le nom de famille:");
                    name = scan.nextLine();
                    updateAuthor.setName(name);
                    authorService.update(idAuthor, updateAuthor);
                }
                case 2 -> {
                    System.out.println(" Entrez le prénom:");
                    firstname = scan.nextLine();
                    updateAuthor.setFirstname(firstname);
                    authorService.update(idAuthor, updateAuthor);
                }
                case 0 -> {
                    System.out.println(" Vous serez rediriger vers le menu principal");
                    stopMethode = true;
                    authors();
                }
                default -> System.out.println(" Veuillez entrez les chiffres suivant: 1 2 ou 0");


            }

        }while( !stopMethode );

    }

    /**
     *
     * LIST AUTHOR
     *
     */
    private static void listAuthor() {

        AuthorServiceImpl authorService = new AuthorServiceImpl();
        List<Author> listAuthors = authorService.getAllAuthors();

        for( Author author : listAuthors ) {
            System.out.println(" N° " +author.getId() +" : " + author.getName() + " " + author.getFirstname() + " .");
        }
    }

}
