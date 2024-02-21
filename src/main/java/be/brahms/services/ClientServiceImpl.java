package be.brahms.services;

import be.brahms.entities.Client;
import be.brahms.repositories.ClientRepository;
import be.brahms.repositories.impl.ClientRepositoryImpl;

import java.util.List;

public class ClientServiceImpl {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void create(Client client) {
        Client existingClientNiss = findClientByNiss( client.getNiss() );

        if( existingClientNiss == null ) {
            clientRepository.create(client);
            System.out.println(" Votre client(e) est bien enregistré(e) ! ");
        } else {
            System.out.println(" Le niss que vous avez entrez existe déjà !");
        }
    }

    private Client findClientByNiss(String niss) {
        List<Client> clients = clientRepository.getAll();

        for( Client client : clients ) {
            if ( client.getNiss().equals(niss) ) {
                return client;
            }
        }

        return null;
    }

}
