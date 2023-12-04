package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateDto {
    public String firstName;
    public String lastName;
    public String surname;
    public String phoneNumber;
    public String username;
    public String password;

    public long userId;
}
