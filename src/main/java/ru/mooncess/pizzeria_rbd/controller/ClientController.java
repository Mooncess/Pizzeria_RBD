package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.ClientCreateDto;
import ru.mooncess.pizzeria_rbd.dto.ClientDto;
import ru.mooncess.pizzeria_rbd.entity.Client;
import ru.mooncess.pizzeria_rbd.service.ClientService;
import ru.mooncess.pizzeria_rbd.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final UserService userService;

    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }
    @GetMapping
    public String getAllClient(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "all_client";
    }
    @GetMapping("byId")
    public String getIngredientById(@RequestParam Integer id, Model model){
        model.addAttribute("oneClient", clientService.getClientById(id));
        return "client";
    }
    @GetMapping("byId/{id}")
    public String getIngredientById(@PathVariable int id, Model model){
        model.addAttribute("oneClient", clientService.getClientById(id));
        return "client";
    }
    @GetMapping("profile")
    public String getProfile(Model model, Authentication authentication) {
        Long userId = userService.loadIdByUsername(authentication.getName());
        model.addAttribute("oneClient", clientService.getClientByUserId(userId));
        return "profile";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/create")
    public String getCreateClient(){
        return "create_client";
    }
    @PostMapping("/create")
    public String createClient(@RequestParam ("firstName") String firstName,
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
        return "redirect:/client";
    }
    @GetMapping("/update/{id}")
    public String getUpdateClient(@PathVariable Integer id, Model model){
        ClientDto client = clientService.getClientById(id);
        client.setIdClient(id);
        model.addAttribute("oneClient", client);
        return "update_client";
    }
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Integer id,
                               @RequestParam ("firstName") String firstName,
                               @RequestParam ("lastName") String lastName,
                               @RequestParam ("surname") String surname,
                               @RequestParam ("phoneNumber") String phoneNumber){
        ClientDto client = new ClientDto();
        client.setIdClient(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setSurname(surname);
        client.setPhoneNumber(phoneNumber);
        clientService.updateClient(client);
        return "redirect:/client/profile";
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

