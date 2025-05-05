package kg.attractor.edufood.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;

import java.util.List;

public interface DishService {
    DishDto findDishById(@NotNull @Positive Long dishId);

    PageHolder<DishDto> findDishByRestaurantId(Long restaurantId, int page, int size);

    List<DishDto> findDishByRestaurantId(Long restaurantId);
}
