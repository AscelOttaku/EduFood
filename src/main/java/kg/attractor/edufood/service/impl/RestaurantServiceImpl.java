package kg.attractor.edufood.service.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.mapper.RestaurantMapper;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.repository.RestaurantRepository;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;
    private final DishService dishService;

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map(restaurantMapper::mapToDto)
                .toList();
    }

    @Override
    public RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId, int page, int size) {
        RestaurantDto restaurantDto = restaurantMapper.mapToDto(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found by id " + restaurantId)));

//        restaurantDto.setDishes(dishService.findDishByRestaurantId(restaurantDto.getId(), page, size));
        return restaurantDto;
    }

    @Override
    public RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId) {
        return restaurantMapper.mapToDto(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found by id " + restaurantId)));
    }

    @Override
    public RestaurantDto findRestaurantByName(@NotBlank String name, int page, int size) {
        RestaurantDto restaurantDto = restaurantMapper.mapToDto(restaurantRepository.findRestaurantByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("restaurant by name " + name + " not found")));

//        restaurantDto.setDishes(dishService.findDishByRestaurantId(restaurantDto.getId(), page, size));
        return restaurantDto;
    }
}
