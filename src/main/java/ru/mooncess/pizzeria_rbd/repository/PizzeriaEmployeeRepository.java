package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.dto.PizzeriaEmployeeDto;
import ru.mooncess.pizzeria_rbd.entity.PizzeriaEmployee;

import java.util.List;

public interface PizzeriaEmployeeRepository {
    List<PizzeriaEmployeeDto> getAllPizzeriaEmployee();
    PizzeriaEmployeeDto getPizzeriaEmployeeById(Integer id);
    void createPizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment);
    void updatePizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment, String dateOfDismissal);
    void deletePizzeriaEmployeeById(Integer id);
}
