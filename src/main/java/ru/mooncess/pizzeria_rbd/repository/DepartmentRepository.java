package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartment();
    Department getDepartmentById(Integer id);
    void createDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartmentById(Integer id);
}
