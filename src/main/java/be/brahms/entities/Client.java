package be.brahms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity( name = "Client")
public class Client {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "niss", unique = true, nullable = false )
    private String niss;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "firstname", nullable = false )
    private String firstname;

    @Column( name = "email", unique = true, nullable = false )
    private String email;

    @Column( name = "phoneNumber")
    private String phoneNumber;

}
