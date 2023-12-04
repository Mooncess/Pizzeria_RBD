package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PizzeriaEmployeeDto {
    public int idPizzeriaEmployee;
    public String nameOfPost;
    public String firstName;
    public String lastName;
    public String surname;
    public String phoneNumber;
    public String dateOfEmployment;
    public int employmentStatus;
    public String dateOfDismissal;
}
