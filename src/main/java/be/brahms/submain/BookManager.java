package be.brahms.submain;

import be.brahms.Main;
import be.brahms.entities.Author;
import be.brahms.entities.Book;
import be.brahms.services.BookServiceImpl;

import java.util.List;
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
                case 4 -> {
                    System.out.println( "\n Liste des livres \n" );
                    listBook();
                }
                case 5 -> {
                    System.out.println( "\n Chercher un livre par titre \n" );
                    listBookByTitle();
                }
                case 6 -> {
                    System.out.println( "\n Liste des livres par Author \n" );
                    listBookByAuthor();
                }
                case 7 -> {
                    System.out.println( "\n Chercher un livre par son ISBN \n" );
                    bookByIsbn();
                }
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
        author.setName(name.toUpperCase());
        author.setFirstname(firstname.toLowerCase());

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

    /**
     *
     * LIST A BOOK FROM DB
     *
     */
    private static void listBook(){

        // Call Book Service
        BookServiceImpl bookService = new BookServiceImpl();

        List<Book> listBooks = bookService.listBooks();

        for ( Book book : listBooks ) {
            System.out.println( "ISBN: " + book.getIsbn() + " => " + " Titre: " + book.getTitle()
            + "\n Qantité: " + book.getQtyBooks() + " | Nombre de page: " + book.getNbPages()
            + "\n Autheur: " + book.getAuthor().getName().toUpperCase() + " " + book.getAuthor().getFirstname().toUpperCase()
            + "\n--- --- --- --- --- --- --- --- --- ---"
            );
        }

    }

    /**
     *
     * LIST A BOOK BY TITLE
     *
     */
    private static void listBookByTitle() {

        // Call Book Service
        BookServiceImpl bookService = new BookServiceImpl();

        //Declare variable
        Scanner scan = new Scanner(System.in);
        String title;

        System.out.println( "Entrez le titre du livre que vous recherchez");
        title = scan.nextLine();

        List<Book> listBooksByTitle = bookService.listBookByTitle(title);

        for( Book book : listBooksByTitle) {
            System.out.println( " - " + book.getIsbn() + " : " + book.getTitle() + " => " + book.getAuthor().getName() + " | " + book.getQtyBooks() + " en stock | " + book.getNbPages() + " pages");
        }


    }

    /**
     *
     * LIST A BOOK BY AUTHOR
     *
     */
    private static void listBookByAuthor() {

        // Call book service
        BookServiceImpl bookService = new BookServiceImpl();

        // Declare variable
        Scanner scan = new Scanner(System.in);
        String name;

        System.out.println( "Entrez le nom de l'auteur" );
        name = scan.nextLine();

        List<Book> listBooks = bookService.listBooksByAuthor(name);

        for( Book book : listBooks) {
            System.out.println("- " + book.getAuthor().getFirstname().charAt(0)  + " - " + book.getTitle() + " | " + book.getQtyBooks() + " en stock");
        }
    }

    private static void bookByIsbn() {

        // Call book service
        BookServiceImpl bookService = new BookServiceImpl();

        // Declare variable
        Scanner scan = new Scanner(System.in);
        int isbn;

        System.out.println( "Entrez le numéro ISBN du livre" );
        isbn = Integer.parseInt(scan.nextLine());

        Book bookIsbn = bookService.getBook(isbn);

        System.out.println("- " + bookIsbn.getTitle() + " => " + bookIsbn.getAuthor().getName().toUpperCase() + " " + bookIsbn.getAuthor().getFirstname() + "\n"
                + bookIsbn.getQtyBooks() + " en stock | " + bookIsbn.getNbPages() + " pages" );


    }

}
