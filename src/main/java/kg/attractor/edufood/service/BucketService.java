package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;

import java.util.Map;

public interface BucketService {
    DishDto addDish(DishDto dish);

    Map<RestaurantDto, DishDto> getBucket();

    void clearDishes();
}
