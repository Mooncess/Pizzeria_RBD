package ru.mooncess.pizzeria_rbd.repository;

import org.springframework.security.core.userdetails.UserDetails;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.entity.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> getAllClients();
    Client getClientById(Integer id);
    void createClient(ClientCreateDto client);
    void updateClient(Client client);
    void deleteClientById(Integer id);
    void deleteAllClients();
    List<Client> orderBy(String field, String asc);
}

