package be.brahms.repositories;

import be.brahms.entities.Book;

import java.util.List;

public interface BookRepository {

    void create(Book book );
    Book update( long id, Book book );
    void delete( long id );
    List<Book> getAllBooks();
    List<Book> getBooksByTitle();
    List<Book> getBooksByAuthor();
    Book getBookByIsbn(int isbn);
    boolean existingIsbn(int isbn);

}
