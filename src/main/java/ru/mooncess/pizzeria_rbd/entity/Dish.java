package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dish {
    private int idDish;
    private String description;
    private int cost;
    private int available;
    List<Ingredient> ingredientList;
}
