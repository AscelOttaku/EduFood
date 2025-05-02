package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface RestaurantMapper {

    @Mapping(target = "dishes", ignore = true)
    RestaurantDto mapToDto(Restaurant restaurant);

    @Mapping(target = "dishes", ignore = true)
    Restaurant mapToEntity(RestaurantDto restaurantDto);
}