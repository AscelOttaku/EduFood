package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMapper {



    public RestaurantDto mapToDto(Restaurant restaurant) {


        return RestaurantDto
                .builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }

    public Restaurant mapToEntity(RestaurantDto dto) {


        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());

        return restaurant;
    }
}
