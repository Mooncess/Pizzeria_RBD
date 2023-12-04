package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.PizzeriaEmployeeDto;
import ru.mooncess.pizzeria_rbd.service.PostService;
import ru.mooncess.pizzeria_rbd.service.PizzeriaEmployeeService;

import java.util.List;

@Controller
@RequestMapping("/pizzeria_employee")
public class PizzeriaEmployeeController {
    private final PizzeriaEmployeeService pizzeriaEmployeeService;
    private final PostService postService;

    public PizzeriaEmployeeController(PizzeriaEmployeeService pizzeriaEmployeeService, PostService postService) {
        this.pizzeriaEmployeeService = pizzeriaEmployeeService;
        this.postService = postService;
    }
    @GetMapping
    public String getAllPizzeriaEmployee(Model model){
        model.addAttribute("pizzeriaEmployees", pizzeriaEmployeeService.getAllPizzeriaEmployees());
        return "all_pizzeria_employee";
    }
    @GetMapping("byId")
    public String getPizzeriaEmployeeById(@RequestParam Integer id, Model model){
        model.addAttribute("onePizzeriaEmployee", pizzeriaEmployeeService.getPizzeriaEmployeeById(id));
        return "pizzeria_employee";
    }

    @GetMapping("byId/{id}")
    public String getPizzeriaEmployeeById(@PathVariable int id, Model model){
        model.addAttribute("onePizzeriaEmployee", pizzeriaEmployeeService.getPizzeriaEmployeeById(id));
        return "pizzeria_employee";
    }

    @GetMapping("/create")
    public String getCreatePizzeriaEmployee(Model model){
        model.addAttribute("postList", postService.getAllPosts());
        return "create_pizzeria_employee";
    }
    @PostMapping("/create")
    public String createPizzeriaEmployee(@RequestParam ("nameOfPost") int post,
                                         @RequestParam ("firstName") String firstName,
                                         @RequestParam ("lastName") String lastName,
                                         @RequestParam ("surname") String surname,
                                         @RequestParam ("phoneNumber") String phoneNumber,
                                         @RequestParam ("dateOfEmployment") String dateOfEmployment){
        PizzeriaEmployeeDto pizzeriaEmployee = new PizzeriaEmployeeDto();
        pizzeriaEmployee.setFirstName(firstName);
        pizzeriaEmployee.setLastName(lastName);
        pizzeriaEmployee.setSurname(surname);
        pizzeriaEmployee.setPhoneNumber(phoneNumber);
        pizzeriaEmployeeService.createPizzeriaEmployee(pizzeriaEmployee, post, dateOfEmployment);
        return "redirect:/pizzeria_employee";
    }
    @GetMapping("/update/{id}")
    public String getUpdatePizzeriaEmployee(@PathVariable Integer id, Model model){
        PizzeriaEmployeeDto pizzeriaEmployee = pizzeriaEmployeeService.getPizzeriaEmployeeById(id);
        pizzeriaEmployee.setIdPizzeriaEmployee(id);
        model.addAttribute("onePizzeriaEmployee", pizzeriaEmployee);
        model.addAttribute("postList", postService.getAllPosts());
        return "update_pizzeria_employee";
    }
    @PostMapping("/update/{id}")
    public String updatePizzeriaEmployee(@PathVariable Integer id,
                                         @RequestParam ("nameOfPost") int post,
                                         @RequestParam ("firstName") String firstName,
                                         @RequestParam ("lastName") String lastName,
                                         @RequestParam ("surname") String surname,
                                         @RequestParam ("phoneNumber") String phoneNumber,
                                         @RequestParam ("dateOfEmployment") String dateOfEmployment,
                                         @RequestParam ("dateOfDismissal") String dateOfDismissal){
        PizzeriaEmployeeDto pizzeriaEmployee = new PizzeriaEmployeeDto();
        pizzeriaEmployee.setIdPizzeriaEmployee(id);
        pizzeriaEmployee.setFirstName(firstName);
        pizzeriaEmployee.setLastName(lastName);
        pizzeriaEmployee.setSurname(surname);
        pizzeriaEmployee.setPhoneNumber(phoneNumber);
        if (dateOfDismissal.isEmpty()) {
            dateOfDismissal = null;
        }
        pizzeriaEmployeeService.updatePizzeriaEmployee(pizzeriaEmployee, post, dateOfEmployment, dateOfDismissal);
        return "redirect:/pizzeria_employee/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deletePizzeriaEmployee(@PathVariable Integer id){
        pizzeriaEmployeeService.deletePizzeriaEmployeeById(id);
        return "redirect:/pizzeria_employee";
    }
}

