package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMapper {

    private final   DishMapper dishMapper;


    public RestaurantDto mapToDto(Restaurant restaurant) {


        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .dishes(PageHolder.of(
                            restaurant.getDishes()
                            .stream()
                            .map(dishMapper::mapToDto)
                            .toList()
                        )
                ).build();
    }

    public Restaurant mapToEntity(RestaurantDto dto) {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());

        restaurant.setDishes(dto.getDishes().getContent()
                    .stream()
                    .map(dishMapper::mapToEntity)
                    .toList());

        return restaurant;
    }
}
