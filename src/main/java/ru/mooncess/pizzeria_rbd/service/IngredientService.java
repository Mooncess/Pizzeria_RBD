package ru.mooncess.pizzeria_rbd.service;

import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;
import ru.mooncess.pizzeria_rbd.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository IngredientRepository, UserService userService) {
        this.ingredientRepository = IngredientRepository;
    }
    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.getAllIngredient();
    }
    public Ingredient getIngredientById(int id){
        return ingredientRepository.getIngredientById(id);
    }
    public void createIngredient(Ingredient ingredient){
        ingredientRepository.createIngredient(ingredient);
    }
    public void updateIngredient(Ingredient Ingredient){
        ingredientRepository.updateIngredient(Ingredient);
    }
    public void deleteIngredientById(Integer id){
        ingredientRepository.deleteIngredientById(id);
    }
}
