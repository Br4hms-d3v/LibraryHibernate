package be.brahms.repositories;

import be.brahms.entities.Book;

import java.util.List;

public interface BookRepository {

    void create(Book book );
    Book update(int isbn, Book book);
    void delete( int isbn );
    List<Book> getAllBooks();
    List<Book> getBooksByTitle( String title);
    List<Book> getBooksByAuthor(String name);
    Book getBookByIsbn(int isbn);
    boolean existingIsbn(int isbn);

}
