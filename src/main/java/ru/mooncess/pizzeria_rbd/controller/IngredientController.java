package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;
import ru.mooncess.pizzeria_rbd.service.IngredientService;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService IngredientService) {
        this.ingredientService = IngredientService;
    }
    @GetMapping
    public String getAllIngredient(Model model){
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "all_ingredient";
    }
    @GetMapping("byId")
    public String getIngredientById(@RequestParam Integer id, Model model){
        model.addAttribute("oneIngredient", ingredientService.getIngredientById(id));
        return "ingredient";
    }

    @GetMapping("byId/{id}")
    public String getIngredientById(@PathVariable int id, Model model){
        model.addAttribute("oneIngredient", ingredientService.getIngredientById(id));
        return "ingredient";
    }

    @GetMapping("/create")
    public String getCreateIngredient(){
        return "create_ingredient";
    }
    @PostMapping("/create")
    public String createIngredient(@RequestParam ("name") String name,
                             @RequestParam ("quantity") int quantity){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
        ingredientService.createIngredient(ingredient);
        return "redirect:/ingredient";
    }
    @GetMapping("/update/{id}")
    public String getUpdateIngredient(@PathVariable Integer id, Model model){
        Ingredient ingredient = ingredientService.getIngredientById(id);
        ingredient.setIdIngredient(id);
        model.addAttribute("oneIngredient", ingredient);
        return "update_ingredient";
    }
    @PostMapping("/update/{id}")
    public String updateIngredient(@PathVariable Integer id, Ingredient ingredient){
        ingredient.setIdIngredient(id);
        ingredientService.updateIngredient(ingredient);
        return "redirect:/ingredient/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Integer id){
        ingredientService.deleteIngredientById(id);
        return "redirect:/ingredient";
    }
}
