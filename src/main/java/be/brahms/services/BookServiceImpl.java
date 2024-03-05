package be.brahms.services;

import be.brahms.entities.Book;
import be.brahms.repositories.AuthorRepository;
import be.brahms.repositories.BookRepository;
import be.brahms.repositories.impl.AuthorRepositoryImpl;
import be.brahms.repositories.impl.BookRepositoryImpl;

public class BookServiceImpl {

    // Call repositories
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // Constructor repositories => Book and Author
    public BookServiceImpl() {
        this.bookRepository = new BookRepositoryImpl();
        this.authorRepository = new AuthorRepositoryImpl();
    }


    // Create a new Book
    public void create(Book book) {

        long authorID;
        String name = book.getAuthor().getName(), firstname = book.getAuthor().getFirstname();

        if( authorRepository.isAuthorExists(book.getAuthor().getName(),  book.getAuthor().getFirstname() ) ) {
            authorID = authorRepository.getAuthorIDByNameAndFirstname(name, firstname);

            if( authorID != 0 ) {
                boolean bookExisting = bookRepository.existingIsbn(book.getIsbn());
                if(!bookExisting){
                    book.getAuthor().setId(authorID);
                    bookRepository.create(book);
                    System.out.println(" Votre livre à bien été enregistré dans la base de données ");
                } else {
                    System.out.println( "Le numéro ISBN: " + book.getIsbn() + " existe déja dans la DB" );
                }

            } else {
                System.out.println(" Il y a eu une erreure lors de la récupération de l'ID de l'auteur ");
            }
        } else {
            System.out.println( "L'auteur n'existe pas dans la base de données!\n" +
                    " Veuillez d'abord l'inscrire dans la DB" );
        }

    }

    // Update a book
    public void update(int isbn, Book book) {
            bookRepository.update(isbn, book);
    }

    // Delete a book
    public void delete( int isbn) {
        Book book = bookRepository.getBookByIsbn(isbn);

        if (book != null && book.getIsbn() != 0 ) {
            bookRepository.delete(isbn);
            System.out.println( "Le livre:" + book.getTitle() + " a bien été supprimé" );
        } else {
            System.out.println( "ISBN avec le numéro: " + isbn + " n'existe pas");
        }
    }

}
