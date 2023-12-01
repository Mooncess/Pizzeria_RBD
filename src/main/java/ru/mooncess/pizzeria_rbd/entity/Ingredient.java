package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
    private int idIngredient;
    private String name;
    private int quantity;
}
