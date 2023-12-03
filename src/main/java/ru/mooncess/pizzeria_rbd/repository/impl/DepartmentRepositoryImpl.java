package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.entity.Department;
import ru.mooncess.pizzeria_rbd.repository.DepartmentRepository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Department> getAllDepartment() {
        String sql = "SELECT * FROM department";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public Department getDepartmentById(Integer id) {
        String sql = "SELECT * FROM department WHERE id_department = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public void createDepartment(Department department) {
        String sql = "INSERT INTO department (name_of_department, number_of_employees) VALUES (?, ?)";
        jdbcTemplate.update(sql, department.getNameOfDepartment(), department.getNumberOfEmployees());
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "UPDATE department SET name_of_department = ?, number_of_employees = ? WHERE id_department = ?";
        jdbcTemplate.update(sql, department.getNameOfDepartment(), department.getNumberOfEmployees(), department.getIdDepartment());
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        String sql = "DELETE FROM department WHERE id_department = ?";
        jdbcTemplate.update(sql, id);
    }
}
