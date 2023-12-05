package ru.mooncess.pizzeria_rbd.service;


import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.dto.ClientDto;
import ru.mooncess.pizzeria_rbd.entity.Client;
import ru.mooncess.pizzeria_rbd.entity.Role;
import ru.mooncess.pizzeria_rbd.entity.User;
import ru.mooncess.pizzeria_rbd.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    private final UserService userService;
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }
    public List<ClientDto> getAllClients(){
        return clientRepository.getAllClients();
    }
    public ClientDto getClientById(int id){
        return clientRepository.getClientById(id);
    }
    public ClientDto getClientByUserId(Long id){
        return clientRepository.getClientByUserId(id);
    }
    public void createClient(ClientCreateDto client){
        User user = new User();
        user.setUsername(client.username);
        user.setPassword(client.password);
        user.setRoleUser(Role.USER);
        client.userId = userService.createUser(user);
        clientRepository.createClient(client);
    }
    public void updateClient(ClientDto client){
        clientRepository.updateClient(client);
    }
    public void deleteClientById(Integer id){
        clientRepository.deleteClientById(id);
    }
    public void deleteAllClients(){
        clientRepository.deleteAllClients();
    }
    public List<Client> orderBy(String field, String asc){
        return clientRepository.orderBy(field, asc);
    }
}

