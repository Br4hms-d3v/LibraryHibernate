package be.brahms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "Author")
public class Author {

    @Id @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "firstname", nullable = false )
    private String firstname;

}
