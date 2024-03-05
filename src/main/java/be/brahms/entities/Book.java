package be.brahms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity( name = "Book")
public class Book {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @NaturalId //est utilisée pour spécifier qu'une propriété d'une entité devrait être traitée comme un identifiant naturel.
    @Column( name = "isbn", nullable = false, unique = true)
    private int isbn;

    @Column( name = "title", nullable = false )
    private String title;

    @Column( name = "qtyBooks")
    private int qtyBooks;

    @Column( name = "nbPage")
    private int nbPages;
    //private Edition edition;

    @ManyToOne
    @JoinColumn( name = "authorId")
    private Author author;

}
