package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishDto {
    public int idDish;
    public String description;
    public int cost;
    public String ingredientList;
    public int available;
}
