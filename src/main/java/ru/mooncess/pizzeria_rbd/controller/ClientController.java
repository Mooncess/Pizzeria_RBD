package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.entity.Client;
import ru.mooncess.pizzeria_rbd.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public String getAllClient(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "all_client";
    }
    @GetMapping("/{id}")
    public String getClientById(@PathVariable int id, Model model){
        model.addAttribute("oneClient", clientService.getClientById(id));
        return "client";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/orderBy")
    public String getClientWithOrder(@RequestParam String field, @RequestParam String asc, Model model){
        model.addAttribute("clients", clientService.orderBy(field, asc));
        return "all_client";
    }
    @GetMapping("/create")
    public String getCreateClient(){
        return "create_client";
    }
    @PostMapping("/create")
    public void createClient(@RequestParam ("firstName") String firstName,
                             @RequestParam ("lastName") String lastName,
                             @RequestParam ("surname") String surname,
                             @RequestParam ("phoneNumber") String phoneNumber,
                             @RequestParam ("username") String username,
                             @RequestParam ("password") String password){
        ClientCreateDto client = new ClientCreateDto();
        client.firstName = firstName;
        client.lastName = lastName;
        client.surname = surname;
        client.phoneNumber = phoneNumber;
        client.username = username;
        client.password = password;
        clientService.createClient(client);
//        return "redirect:/client";
    }
    @GetMapping("/update/{id}")
    public String getUpdateClient(@PathVariable Integer id, Model model){
        Client client = clientService.getClientById(id);
        client.setIdClient(id);
        model.addAttribute("oneClient", client);
        return "update_client";
    }
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Integer id, Client client){
        client.setIdClient(id);
        clientService.updateClient(client);
        return "redirect:/client/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Integer id){
        clientService.deleteClientById(id);
        return "redirect:/client";
    }
    @PostMapping("/deleteAll")
    public String deleteAllClient() {
        clientService.deleteAllClients();
        return "redirect:/client";
    }
}

