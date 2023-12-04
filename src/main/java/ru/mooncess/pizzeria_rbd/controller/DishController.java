package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.DishDto;
import ru.mooncess.pizzeria_rbd.entity.Dish;
import ru.mooncess.pizzeria_rbd.service.DishService;
import ru.mooncess.pizzeria_rbd.service.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;
    private final IngredientService ingredientService;

    public DishController(DishService dishService, IngredientService ingredientService) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
    }
    @GetMapping
    public String getAllDish(Model model){
        model.addAttribute("dishs", dishService.getAllDishs());
        return "all_dish";
    }

    @GetMapping("/admin")
    public String getAllDishAdmin(Model model){
        model.addAttribute("dishs", dishService.getAllDishsAdmin());
        return "all_dish_admin";
    }
    @GetMapping("byId")
    public String getDishById(@RequestParam Integer id, Model model){
        model.addAttribute("oneDish", dishService.getDishById(id));
        return "dish";
    }

    @GetMapping("byId/{id}")
    public String getDishById(@PathVariable int id, Model model){
        model.addAttribute("oneDish", dishService.getDishById(id));
        return "dish";
    }

    @GetMapping("/create")
    public String getCreateDish(Model model){
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "create_dish";
    }
    @PostMapping("/create")
    public String createDish(@RequestParam ("description") String description,
                             @RequestParam ("cost") int cost,
                             @RequestParam ("selectedIngredients") List<Integer> selectedIngredients){
        DishDto dish = new DishDto();
        dish.setDescription(description);
        dish.setCost(cost);
        String list = "";
        for (int i : selectedIngredients) {
            list = list + i + ",";
        }
        list = list.substring(0, list.length() - 1);
        dish.setIngredientList(list);
        dishService.createDish(dish);
        return "redirect:/dish";
    }
    @GetMapping("/update/{id}")
    public String getUpdateDish(@PathVariable Integer id, Model model){
        DishDto dish = dishService.getDishById(id);
        dish.setIdDish(id);
        model.addAttribute("oneDish", dish);
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "update_dish";
    }
    @PostMapping("/update/{id}")
    public String updateDish(@PathVariable Integer id, @RequestParam ("description") String description,
                             @RequestParam ("cost") int cost,
                             @RequestParam ("selectedIngredients") List<Integer> selectedIngredients){
        DishDto dish = new DishDto();
        dish.setDescription(description);
        dish.setCost(cost);
        String list = "";
        for (int i : selectedIngredients) {
            list = list + i + ",";
        }
        list = list.substring(0, list.length() - 1);
        dish.setIngredientList(list);
        dish.setIdDish(id);
        dishService.updateDish(dish);
        System.out.println(dish.getIdDish());
        System.out.println(dish.getIngredientList());
        return "redirect:/dish/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Integer id){
        dishService.deleteDishById(id);
        return "redirect:/dish";
    }
}
