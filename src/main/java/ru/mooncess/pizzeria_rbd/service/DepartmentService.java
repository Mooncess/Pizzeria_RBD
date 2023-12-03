package ru.mooncess.pizzeria_rbd.service;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.entity.Department;
import ru.mooncess.pizzeria_rbd.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository DepartmentRepository, UserService userService) {
        this.departmentRepository = DepartmentRepository;
    }
    public List<Department> getAllDepartments(){
        return departmentRepository.getAllDepartment();
    }
    public Department getDepartmentById(int id){
        return departmentRepository.getDepartmentById(id);
    }
    public void createDepartment(Department department){
        departmentRepository.createDepartment(department);
    }
    public void updateDepartment(Department Department){
        departmentRepository.updateDepartment(Department);
    }
    public void deleteDepartmentById(Integer id){
        departmentRepository.deleteDepartmentById(id);
    }
}
