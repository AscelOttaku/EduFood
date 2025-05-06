package kg.attractor.edufood.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.dto.RestaurantDto;

public interface RestaurantService {
    PageHolder<RestaurantDto> getAllRestaurants(int page, int size);

    RestaurantDto findRestaurantById(Long restaurantId, int page, int size);

    RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId);

    PageHolder<RestaurantDto> findRestaurantByName(@NotBlank String name, int page, int size);
}
