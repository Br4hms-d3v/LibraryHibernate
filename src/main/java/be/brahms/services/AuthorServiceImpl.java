package be.brahms.services;

import be.brahms.entities.Author;
import be.brahms.repositories.AuthorRepository;
import be.brahms.repositories.impl.AuthorRepositoryImpl;

import java.util.List;

public class AuthorServiceImpl {

    // Call repository
    private final AuthorRepository authorRepository;

    // Constructor repository
    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
    }

    /**
     * @return a new author
     */
    public void create(Author author) {

        if( authorRepository.isAuthorExists(author.getName(), author.getFirstname()) ){
            System.out.println("L'auteur avec le nom: " + author.getName()+ " et le prénom : " + author.getFirstname() + " existe déjà dans la base de données!");
        } else {
            authorRepository.create(author);
            System.out.println(" Votre author est bien enregistré ! ");
        }
    }

    public void update( long id, Author author ) {
        authorRepository.update(id, author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    public void delete(long id) {
        Author author = authorRepository.findById(id);

        if (author != null && author.getId() != 0) {
            authorRepository.delete(id);
            System.out.println(" L'auteur n° " + author.getId() + " => " + author.getName() + " a bien été supprimé");
        } else {
            System.out.println(" l'auteur n° " + id + " n'existe pas");
        }
    }
}
