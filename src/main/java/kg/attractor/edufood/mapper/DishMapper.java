package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishDto mapToDto(Dish dish);

    Dish mapToEntity(DishDto dishDto);
}
