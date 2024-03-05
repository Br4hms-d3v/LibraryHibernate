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

    @NaturalId
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
