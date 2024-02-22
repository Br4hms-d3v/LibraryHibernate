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
        Client existingClientNiss = findClientByNiss( client.getNiss() );

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
    private Client findClientByNiss(String niss) {
        List<Client> clients = clientRepository.getAll();

        for( Client client : clients ) {
            if ( client.getNiss().equals(niss) ) {
                return client;
            }
        }
        return null;
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

}
