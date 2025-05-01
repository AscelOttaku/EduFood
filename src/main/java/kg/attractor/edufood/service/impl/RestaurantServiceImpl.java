package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.mapper.RestaurantMapper;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.repository.RestaurantRepository;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;


    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurantMapper::mapToDto).toList();
    }
}
