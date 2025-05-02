package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DishMapper {

    private RestaurantMapper restaurantMapper;

    @Autowired
    private  void setVacancyMapper(@Lazy  RestaurantMapper restaurantMapper) {
        this.restaurantMapper=restaurantMapper;
    }

    public DishDto mapToDto(Dish dish) {

        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .restaurantId(restaurantMapper.mapToDto(dish.getRestaurant()))
                .price(dish.getPrice())
                .build();
    }

    public Dish mapToEntity(DishDto dto) {

        Dish dish = new Dish();
        dish.setId(dto.getId());
        dish.setName(dto.getName());

        dish.setRestaurant(restaurantMapper.mapToEntity(dto.getRestaurantId()));
        dish.setPrice(dto.getPrice());
        return dish;
    }
}
