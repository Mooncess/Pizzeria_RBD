package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PizzeriaEmployee {
    private int idPizzeriaEmployee;
    private Post post;
    private FullName fullName;
    private PhoneNumber phoneNumber;
    private Date dateOfEmployment;
    private int employmentStatus;
    private Date dateOfDismissal;
}
