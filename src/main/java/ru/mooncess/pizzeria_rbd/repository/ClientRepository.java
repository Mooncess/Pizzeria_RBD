package ru.mooncess.pizzeria_rbd.repository;

import org.springframework.security.core.userdetails.UserDetails;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.dto.ClientDto;
import ru.mooncess.pizzeria_rbd.entity.Client;

import java.util.List;

public interface ClientRepository {
    List<ClientDto> getAllClients();
    ClientDto getClientById(Integer id);
    ClientDto getClientByUserId(Long id);
    void createClient(ClientCreateDto client);
    void updateClient(ClientDto client);
    void deleteClientById(Integer id);
    void deleteAllClients();
    List<Client> orderBy(String field, String asc);
    void updateNumberOrders(int clientId);
}

