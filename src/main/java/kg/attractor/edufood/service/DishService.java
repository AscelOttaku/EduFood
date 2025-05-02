package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;

public interface DishService {
    DishDto findDishById(Long dishId);

    PageHolder<DishDto> findDishByRestaurantId(Long restaurantId, int page, int size);
}
