package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Orders {
    private int idOrder;
    private Client client;
    private String description;
    private int totalCost;
    private StatusOfOrder statusOfOrder;
    private Date dateOfCreation;
    private PizzeriaEmployee pizzeriaEmployee;
    List<Dish> dishList;
}
