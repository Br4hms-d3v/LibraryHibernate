package be.brahms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "Reservation")
public class Reservation {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "startBorrow", nullable = false )
    private LocalDate startBorrow;

    @Column( name = "endBorrow", nullable = false )
    private LocalDate endBorrow;

    @Column( name = "isBack", nullable = false )
    private boolean isBack;

    @OneToMany(mappedBy = "reservation")
    private List<Book> books;

    @ManyToOne
    @JoinColumn( name = "clientId" )
    private Client client;

}
