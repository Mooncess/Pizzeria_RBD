package ru.mooncess.pizzeria_rbd.service;

import org.springframework.stereotype.Service;
import ru.mooncess.pizzeria_rbd.dto.DishDto;
import ru.mooncess.pizzeria_rbd.entity.Dish;
import ru.mooncess.pizzeria_rbd.repository.DishRepository;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository DishRepository, UserService userService) {
        this.dishRepository = DishRepository;
    }
    public List<DishDto> getAllDishs(){
        return dishRepository.getAllDish();
    }
    public List<DishDto> getAllDishsAdmin(){
        return dishRepository.getAllDishAdmin();
    }
    public DishDto getDishById(int id){
        return dishRepository.getDishById(id);
    }
    public void createDish(DishDto dish){
        dishRepository.createDish(dish);
    }
    public void updateDish(DishDto Dish){
        dishRepository.updateDish(Dish);
    }
    public void deleteDishById(Integer id){
        dishRepository.deleteDishById(id);
    }

}
