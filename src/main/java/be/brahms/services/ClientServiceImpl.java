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

}
