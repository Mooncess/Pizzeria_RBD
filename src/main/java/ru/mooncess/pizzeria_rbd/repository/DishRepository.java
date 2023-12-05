package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.dto.DishDto;
import ru.mooncess.pizzeria_rbd.entity.Dish;

import java.util.List;

public interface DishRepository {
    List<DishDto> getAllDish();
    List<DishDto> getAllDishAdmin();
    DishDto getDishById(Integer id);
    void createDish(DishDto dish);
    void updateDish(DishDto dish);
    void deleteDishById(Integer id);
    int calcTotalCost(List<Integer> selectedDishes);
}
