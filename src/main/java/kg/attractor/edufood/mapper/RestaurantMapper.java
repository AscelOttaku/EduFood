package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface RestaurantMapper {

    RestaurantDto mapToDto(Restaurant restaurant);

    Restaurant mapToEntity(RestaurantDto restaurantDto);
}