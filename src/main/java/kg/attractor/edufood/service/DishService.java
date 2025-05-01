package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.DishDto;

public interface DishService {
    DishDto findDishById(Long dishId);
}
