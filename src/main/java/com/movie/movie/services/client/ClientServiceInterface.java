package com.movie.movie.services.client;

import java.util.List;

import com.movie.movie.entities.ClientEntity;

public interface ClientServiceInterface {
    List<ClientEntity> getAllClients();

    ClientEntity saveClient(ClientEntity client);

    ClientEntity getClientById(Long id);

    ClientEntity getClientByEmail(String email);

    ClientEntity updateClient(ClientEntity client);

    void deleteClientById(Long id);
}
