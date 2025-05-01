package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();
}
