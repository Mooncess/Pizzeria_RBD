package ru.mooncess.pizzeria_rbd.repository;

import ru.mooncess.pizzeria_rbd.entity.Client;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> getAllIngredient();
    Ingredient getIngredientById(Integer id);
    void createIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    void deleteIngredientById(Integer id);
}
