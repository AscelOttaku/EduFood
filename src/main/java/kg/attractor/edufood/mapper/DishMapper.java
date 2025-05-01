package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    DishDto mapToDto(Dish dish);

    @Mapping(target = "restaurant.id", source = "restaurantId")
    Dish mapToEntity(DishDto dishDto);
}
