package be.brahms.services;

import be.brahms.entities.Client;
import be.brahms.repositories.ClientRepository;
import be.brahms.repositories.impl.ClientRepositoryImpl;

import java.util.List;

public class ClientServiceImpl {

    // Call my repository
    private final ClientRepository clientRepository;

    //Constructor
    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    /**
     * @return All Clients
     */
    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * @return a new Client
     */
    public void create(Client client) {
        Client existingClientNiss = getByNiss( client.getNiss() );

        if( existingClientNiss == null ) {
            clientRepository.create(client);
            System.out.println(" Votre client(e) est bien enregistré(e) ! ");
        } else {
            System.out.println(" Le niss que vous avez entrez existe déjà !");
        }
    }

    /**
     * @return a NISS Client
     */
    public Client getByNiss(String niss) {

        Client existingClientNiss = clientRepository.getByNiss(niss);

        if( existingClientNiss != null ) {
            return existingClientNiss;
        } else {
            System.out.println(" Le client avec le Niss: " + existingClientNiss +" n'existe pas dans la base de données! ");
            return null;
        }
    }

    /**
     * Update client
     */
    public void update( long id, Client client ) {
        clientRepository.update(id, client);
    }

    /**
     *
     * Delete a client by id
     */
    public void delete(long id) {
        // Get All data from idClient
        Client client = clientRepository.findById(id);

        if ( client != null && client.getId() != null ){
            clientRepository.delete(id);
            System.out.println(" Le client: " + client.getName() + " " + client.getFirstname() + " a bien été supprimé! ");
        } else {
            System.out.println(" Le client: " + id + " n'existe pas! ");
        }

    }

    /**
     *
     * Find a client by his name
     */
    public List<Client> getClientsByName( String name) {

        return clientRepository.getByName(name);
    }

}
