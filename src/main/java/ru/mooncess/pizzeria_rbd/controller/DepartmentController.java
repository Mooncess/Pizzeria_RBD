package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.entity.Department;
import ru.mooncess.pizzeria_rbd.service.DepartmentService;


@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService DepartmentService) {
        this.departmentService = DepartmentService;
    }
    @GetMapping
    public String getAllDepartment(Model model){
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "all_department";
    }
    @GetMapping("byId")
    public String getDepartmentById(@RequestParam Integer id, Model model){
        model.addAttribute("oneDepartment", departmentService.getDepartmentById(id));
        return "department";
    }

    @GetMapping("byId/{id}")
    public String getDepartmentById(@PathVariable int id, Model model){
        model.addAttribute("oneDepartment", departmentService.getDepartmentById(id));
        return "department";
    }

    @GetMapping("/create")
    public String getCreateDepartment(){
        return "create_department";
    }
    @PostMapping("/create")
    public String createDepartment(@RequestParam ("name_of_department") String name_of_department){
        Department department = new Department();
        department.setNameOfDepartment(name_of_department);
        department.setNumberOfEmployees(0);
        departmentService.createDepartment(department);
        return "redirect:/department";
    }
    @GetMapping("/update/{id}")
    public String getUpdateDepartment(@PathVariable Integer id, Model model){
        Department department = departmentService.getDepartmentById(id);
        department.setIdDepartment(id);
        model.addAttribute("oneDepartment", department);
        return "update_department";
    }
    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Integer id, Department department){
        department.setIdDepartment(id);
        System.out.println(department.getNameOfDepartment());
        departmentService.updateDepartment(department);
        return "redirect:/department/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartmentById(id);
        return "redirect:/department";
    }
}
