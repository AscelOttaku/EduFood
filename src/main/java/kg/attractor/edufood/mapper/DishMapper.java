package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DishMapper {

    @Lazy
    @Autowired
    private RestaurantService restaurantService;

    public DishDto mapToDto(Dish dish) {

        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .restaurant(restaurantService.findRestaurantById(dish.getRestaurant().getId()))
                .price(dish.getPrice())
                .build();
    }

    public Dish mapToEntity(DishDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getRestaurant().getId());

        Dish dish = new Dish();
        dish.setId(dto.getId());
        dish.setName(dto.getName());
        dish.setRestaurant(restaurant);
        dish.setPrice(dto.getPrice());
        return dish;
    }
}
