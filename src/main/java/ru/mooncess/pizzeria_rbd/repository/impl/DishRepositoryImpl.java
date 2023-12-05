package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.dto.DishDto;
import ru.mooncess.pizzeria_rbd.dto.IngredientDto;
import ru.mooncess.pizzeria_rbd.entity.Dish;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;
import ru.mooncess.pizzeria_rbd.repository.DishRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    private final JdbcTemplate jdbcTemplate;

    public DishRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DishDto> getAllDish() {
        String sql = "SELECT * FROM dish WHERE available = 1";
        List<DishDto> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DishDto.class));
        for (DishDto dish : list) {
            List<IngredientDto> ingredientList = jdbcTemplate.query(
                    "SELECT ingredient.id_ingredient, ingredient.name " +
                            "FROM ingredient_has_dish " +
                            "JOIN ingredient ON ingredient.id_ingredient = ingredient_has_dish.ingredient_id_ingredient " +
                            "WHERE dish_id_dish = ?;",
                    new Object[]{dish.getIdDish()}, new BeanPropertyRowMapper<>(IngredientDto.class));
            String ingredients = "";
            for (IngredientDto i : ingredientList) {
                ingredients = ingredients + i.name + "\n";
            }
            dish.setIngredientList(ingredients);
        }
        return list;
    }

    @Override
    public List<DishDto> getAllDishAdmin() {
        String sql = "SELECT * FROM dish";
        List<DishDto> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DishDto.class));
        for (DishDto dish : list) {
            List<IngredientDto> ingredientList = jdbcTemplate.query(
                    "SELECT ingredient.id_ingredient, ingredient.name " +
                            "FROM ingredient_has_dish " +
                            "JOIN ingredient ON ingredient.id_ingredient = ingredient_has_dish.ingredient_id_ingredient " +
                            "WHERE dish_id_dish = ?;",
                    new Object[]{dish.getIdDish()}, new BeanPropertyRowMapper<>(IngredientDto.class));
            String ingredients = "";
            for (IngredientDto i : ingredientList) {
                ingredients = ingredients + i.name + "\n";
            }
            dish.setIngredientList(ingredients);
        }
        return list;
    }

    @Override
    public DishDto getDishById(Integer id) {
        String sql = "SELECT * FROM dish WHERE id_dish = ?";
        DishDto dish = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(DishDto.class));
        List<IngredientDto> ingredientList = jdbcTemplate.query(
                "SELECT ingredient.id_ingredient, ingredient.name " +
                        "FROM ingredient_has_dish " +
                        "JOIN ingredient ON ingredient.id_ingredient = ingredient_has_dish.ingredient_id_ingredient " +
                        "WHERE dish_id_dish = ?;",
                new Object[]{dish.getIdDish()}, new BeanPropertyRowMapper<>(IngredientDto.class));
        String ingredients = "";
        for (IngredientDto i : ingredientList) {
            ingredients = ingredients + i.name + "\n";
        }
        dish.setIngredientList(ingredients);
        return dish;
    }

    @Override
    public void createDish(DishDto dish) {
        String sql = "CALL create_dish(?, ?, ?);";
        jdbcTemplate.update(sql, dish.description, dish.cost, dish.ingredientList);
    }

    @Override
    public void updateDish(DishDto dish) {
        String sql = "CALL update_dish(?, ?, ?, ?);";
        jdbcTemplate.update(sql, dish.idDish, dish.description, dish.cost, dish.ingredientList);
    }

    @Override
    public void deleteDishById(Integer id) {
        String sql = "DELETE FROM dish WHERE id_dish = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public int calcTotalCost(List<Integer> selectedDishes) {
        int totalCost = 0;
        for (int i : selectedDishes) {
            DishDto dishDto = jdbcTemplate.queryForObject(
                    "SELECT cost " +
                            "FROM dish " +
                            "WHERE id_dish = ?;",
                    new Object[]{i}, new BeanPropertyRowMapper<>(DishDto.class));
            totalCost+=dishDto.getCost();
        }
        return totalCost;
    }
}
