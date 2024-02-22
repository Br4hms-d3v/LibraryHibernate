package be.brahms.repositories;

import be.brahms.entities.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getAll();
    Client getByNiss( String niss );
    List<Client> getByName( String name );
    void create( Client client );
    Client update(long id, Client client );
    void delete( long id );
    Client findById(long id);
}
