package ru.mooncess.pizzeria_rbd.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mooncess.pizzeria_rbd.entity.Ingredient;
import ru.mooncess.pizzeria_rbd.repository.IngredientRepository;

import java.util.List;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    public IngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ingredient.class));
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        String sql = "SELECT * FROM ingredient WHERE id_ingredient = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Ingredient.class));
    }

    @Override
    public void createIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient (name, quantity) VALUES (?, ?)";
        jdbcTemplate.update(sql, ingredient.getName(), ingredient.getQuantity());
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        String sql = "UPDATE ingredient SET name = ?, quantity = ? WHERE id_ingredient = ?";
        jdbcTemplate.update(sql, ingredient.getName(), ingredient.getQuantity(), ingredient.getIdIngredient());
    }

    @Override
    public void deleteIngredientById(Integer id) {
        String sql = "DELETE FROM ingredient WHERE id_ingredient = ?";
        jdbcTemplate.update(sql, id);
    }
}
