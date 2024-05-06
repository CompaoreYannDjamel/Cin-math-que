package com.movie.movie.services.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.entities.ClientEntity;
import com.movie.movie.repositories.ClientRepository;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public ClientEntity getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public ClientEntity saveClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientEntity updateClient(ClientEntity client) {
        return clientRepository.save(client);
    }

}
