package ru.mooncess.pizzeria_rbd.service;

import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.dto.PizzeriaEmployeeDto;
import ru.mooncess.pizzeria_rbd.repository.PizzeriaEmployeeRepository;

import java.util.List;

@Service
public class PizzeriaEmployeeService {
    private final PizzeriaEmployeeRepository pizzeriaEmployeeRepository;

    public PizzeriaEmployeeService(PizzeriaEmployeeRepository PizzeriaEmployeeRepository, UserService userService) {
        this.pizzeriaEmployeeRepository = PizzeriaEmployeeRepository;
    }
    public List<PizzeriaEmployeeDto> getAllPizzeriaEmployees(){
        return pizzeriaEmployeeRepository.getAllPizzeriaEmployee();
    }
    public PizzeriaEmployeeDto getPizzeriaEmployeeById(int id){
        return pizzeriaEmployeeRepository.getPizzeriaEmployeeById(id);
    }
    public void createPizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment){
        System.out.println(dateOfEmployment);
        pizzeriaEmployeeRepository.createPizzeriaEmployee(pizzeriaEmployee, post, dateOfEmployment);
    }
    public void updatePizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment, String dateOfDismissal){
        pizzeriaEmployeeRepository.updatePizzeriaEmployee(pizzeriaEmployee, post, dateOfEmployment, dateOfDismissal);
    }
    public void deletePizzeriaEmployeeById(Integer id){
        pizzeriaEmployeeRepository.deletePizzeriaEmployeeById(id);
    }
}
