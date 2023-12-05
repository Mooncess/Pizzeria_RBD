package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;
import ru.mooncess.pizzeria_rbd.entity.FullName;
import ru.mooncess.pizzeria_rbd.entity.PhoneNumber;
import ru.mooncess.pizzeria_rbd.entity.User;

@Getter
@Setter
public class ClientDto {
    public int idClient;
    public String firstName;
    public String lastName;
    public String surname;
    public String phoneNumber;
    public int numberOfOrders;
    public float personalDiscount;
}
