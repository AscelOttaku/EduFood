package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;

import java.util.List;
import java.util.Map;

public interface BucketService {
    DishDto addDish(DishDto dish);

    Map<RestaurantDto, List<DishDto>> getBucket();

    void clearDishes();

    Integer defineQuantity();
}
