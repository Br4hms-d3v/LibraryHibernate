package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Author;
import be.brahms.entities.Book;
import be.brahms.services.BookServiceImpl;

import java.util.Scanner;

public class BookManager {

    public static void books() {

        // Declare variables
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean stopMethode = false;

        do {

            // This is the main for my application
            System.out.println("""
                     Entrez le numéro que vous souhaitez y aller\s
                    1- Ajouter un livre\s
                    2- Modifier un livre\s
                    3- Supprimer un livre\s
                    4- Liste de tout les livres\s
                    5- Chercher un livre via son titre\s
                    6- Chercher un livre via son auteur\s
                    7- Chercher un livre via son ISBN\s
                    0- Pour revenir en arrière""");
            choice = Integer.parseInt( scan.nextLine() );

            switch(choice) {
                case 1 -> {
                    System.out.println(" \n Ajouter un livre \n ");
                    addBook();
                }
                case 2 -> {
                    System.out.println(" \n Modifier un livre \n ");
                    updateBook();
                }
                case 3 -> {
                    System.out.println(" \n Supprimer un livre \n ");
                    deleteBook();
                }
                case 4 -> {}
                case 5 -> {}
                case 6 -> {}
                case 7 -> {}
                case 0 -> {
                    System.out.println( "Vous serez redirigé vers le menu principal" );
                    stopMethode = true;
                    Main.appLibrary();
                }
                default -> System.out.println(" Veuillez choisir le numéro entre 1 à 7 ou le 0 pour quitter");
            }

        } while( !stopMethode );

    }

    /**
     *
     * ADD A BOOK TO DB
     *
     */
    private static void addBook() {

        // Call bookService
        BookServiceImpl bookService = new BookServiceImpl();

        //Declare variables
        Scanner scan = new Scanner(System.in);
        Book newBook = new Book();
        Author author = new Author();

        String title, name, firstname;
        int qtyBooks, nbPages, isbn;

        System.out.println(" Entrez le titre du livre");
        title = scan.nextLine();

        System.out.println(" Entrez le le numéro du ISBN");
        isbn = Integer.parseInt(scan.nextLine());

        System.out.println( "Entrez la quantité recu de ce livre" );
        qtyBooks = Integer.parseInt(scan.nextLine());

        System.out.println( "Entrez le nombre de page" );
        nbPages = Integer.parseInt(scan.nextLine());

        System.out.println( "Entrez le nom de l'auteur" );
        name = scan.nextLine();

        System.out.println( "Entrez le prénom de l'auteur" );
        firstname = scan.nextLine();

        // Take all variables and persist to DataBase
        newBook.setTitle(title);
        newBook.setIsbn(isbn);
        newBook.setQtyBooks(qtyBooks);
        newBook.setNbPages(qtyBooks);
        newBook.setNbPages(nbPages);

        // Take data for AUTHOR
        author.setName(name);
        author.setFirstname(firstname);

        // Persist author inside book
        newBook.setAuthor(author);

        // Send Object to my Service;
        bookService.create(newBook);
    }

    /**
     *
     * UPDATE A BOOK TO DB
     *
     */
    private static void updateBook(){
        // Call bookService
        BookServiceImpl bookService = new BookServiceImpl();

        //Declare variables
        Scanner scan = new Scanner(System.in);
        Book updateBook = new Book();
        Author updateAuthor = new Author();

        String title;
        long idAuthor;
        int qtyBooks, nbPages, isbn, choice;
        boolean stopMethode = false;

        System.out.println( "Veuillez entrez le numéro de ISBN du livre" );
        isbn = Integer.parseInt(scan.nextLine());

        do {
            System.out.println("""
                    1- Titre\s
                    2- Quantité\s 
                    3- Nombre de page\s
                    4- ID de l'auteur\s
                    0- Pour revenir en arrière\s
                    """);
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println( "Entrez le titre du livre" );
                    title = scan.nextLine();
                    updateBook.setTitle(title);
                    bookService.update(isbn, updateBook);
                }
                case 2 -> {
                    System.out.println( "Entrez  la quantité disponible" );
                    qtyBooks = Integer.parseInt(scan.nextLine());
                    updateBook.setQtyBooks(qtyBooks);
                    bookService.update(isbn, updateBook);
                }
                case 3 -> {
                    System.out.println( "Entrez le nombre de page" );
                    nbPages = Integer.parseInt(scan.nextLine());
                    updateBook.setQtyBooks(nbPages);
                    bookService.update(isbn, updateBook);
                }
                case 4 -> {
                    System.out.println( "Entrez l'ID de l'auteur" );
                    idAuthor = Long.parseLong(scan.nextLine());

                    updateAuthor.setId(idAuthor);

                    updateBook.setAuthor(updateAuthor);
                    bookService.update(isbn, updateBook);
                }
                case 0 -> {
                    System.out.println( "Vous serez rediriger vers le menu principal");
                    stopMethode = true;
                    books();
                }
                default -> System.out.println( "Veuillez Entrer le numéro entre 1 à 4 ou le 0 pour quitter" );
            }

        } while ( !stopMethode );
    }

    /**
     *
     * DELETE A BOOK TO DB
     *
     */
    private static void deleteBook() {
        // Call book Service
        BookServiceImpl bookService = new BookServiceImpl();

        //Declare variables
        Scanner scan = new Scanner(System.in);
        int isbn;

        System.out.println( "Entrez le n° ISBN");
        isbn = scan.nextInt();

        bookService.delete(isbn);
    }

}
