package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersCreateDto {
    public int idOrder;
    public int clientIdClient;
    public String description;
    public float totalCost;
    public int status;
    public String dateOfCreation;
}
