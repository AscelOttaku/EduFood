package kg.attractor.edufood.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();

    RestaurantDto findRestaurantById(Long restaurantId, int page, int size);

    RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId);

    RestaurantDto findRestaurantByName(@NotBlank String name, int page, int size);
}
