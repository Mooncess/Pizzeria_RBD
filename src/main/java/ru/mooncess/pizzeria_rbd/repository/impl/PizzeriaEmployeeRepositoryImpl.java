package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.PizzeriaEmployeeDto;
import ru.mooncess.pizzeria_rbd.entity.PizzeriaEmployee;
import ru.mooncess.pizzeria_rbd.repository.PizzeriaEmployeeRepository;

import java.util.List;

@Repository
public class PizzeriaEmployeeRepositoryImpl implements PizzeriaEmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public PizzeriaEmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PizzeriaEmployeeDto> getAllPizzeriaEmployee() {
        String sql = "SELECT pizzeria_employee.id_pizzeria_employee, post.name_of_post, " +
                "full_name.first_name, full_name.last_name, full_name.surname, " +
                "phone_number.phone_number, " +
                "pizzeria_employee.date_of_employment, pizzeria_employee.employment_status, pizzeria_employee.date_of_dismissal " +
                "FROM pizzeria_employee " +
                "JOIN post ON pizzeria_employee.post_id_post = post.id_post " +
                "JOIN full_name ON pizzeria_employee.full_name_id_full_name = full_name.id_full_name " +
                "JOIN phone_number ON pizzeria_employee.phone_number_id_phone_number = phone_number.id_phone_number;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PizzeriaEmployeeDto.class));
    }

    @Override
    public PizzeriaEmployeeDto getPizzeriaEmployeeById(Integer id) {
        String sql = "SELECT pizzeria_employee.id_pizzeria_employee, post.name_of_post, " +
                "full_name.first_name, full_name.last_name, full_name.surname, " +
                "phone_number.phone_number, " +
                "pizzeria_employee.date_of_employment, pizzeria_employee.employment_status, pizzeria_employee.date_of_dismissal " +
                "FROM pizzeria_employee " +
                "JOIN post ON pizzeria_employee.post_id_post = post.id_post " +
                "JOIN full_name ON pizzeria_employee.full_name_id_full_name = full_name.id_full_name " +
                "JOIN phone_number ON pizzeria_employee.phone_number_id_phone_number = phone_number.id_phone_number WHERE id_pizzeria_employee = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PizzeriaEmployeeDto.class));
    }

    @Override
    public void createPizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment) {
        String sql = "CALL CreatePizzeriaEmployee(?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, post, pizzeriaEmployee.firstName, pizzeriaEmployee.lastName, pizzeriaEmployee.surname, pizzeriaEmployee.phoneNumber, dateOfEmployment);
    }

    @Override
    public void updatePizzeriaEmployee(PizzeriaEmployeeDto pizzeriaEmployee, int post, String dateOfEmployment, String dateOfDismissal) {
        String sql = "CALL UpdatePizzeriaEmployee(?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, pizzeriaEmployee.idPizzeriaEmployee, post, pizzeriaEmployee.firstName, pizzeriaEmployee.lastName, pizzeriaEmployee.surname, pizzeriaEmployee.phoneNumber, dateOfEmployment, dateOfDismissal);
    }

    @Override
    public void deletePizzeriaEmployeeById(Integer id) {
        String sql = "DELETE FROM pizzeria_employee WHERE id_pizzeria_employee = ?";
        jdbcTemplate.update(sql, id);
    }
}
