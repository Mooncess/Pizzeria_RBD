package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDto {
    public int idOrder;
    public int clientIdClient;
    public String description;
    public float totalCost;
    public String status;
    public String  dateOfCreation;
}
