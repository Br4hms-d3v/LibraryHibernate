package be.brahms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity( name = "Book")
public class Book {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "title", nullable = false )
    private String title;

    @Column( name = "qtyBooks")
    private int qtyBooks;

    @Column( name = "nbPage")
    private int nbPage;
    //private Edition edition;

    @ManyToOne
    @JoinColumn( name = "authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
