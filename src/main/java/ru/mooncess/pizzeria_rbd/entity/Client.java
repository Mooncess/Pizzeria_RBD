package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private int idClient;
    private FullName fullName;
    private int numberOfOrders;
    private float personalDiscount;
    private PhoneNumber phoneNumber;
    private User user;
}
