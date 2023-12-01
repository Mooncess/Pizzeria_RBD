package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
    private int idDepartment;
    private String nameOfDepartment;
    private int numberOfEmployees;
}
