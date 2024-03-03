package be.brahms.repositories;

import be.brahms.entities.Author;

import java.util.List;

public interface AuthorRepository {

    void create( Author author);
    Author update( long id, Author author);
    List<Author> getAll();
    List<Author> getByName();
    void delete( long id );
    boolean isAuthorExists(String name, String firstname);
}
