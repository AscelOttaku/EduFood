package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "dishId", source = "dish.id")
    OrderDto mapToDto(Order order);

    @Mapping(target = "dish.id", source = "dishId")
    Order mapToEntity(OrderDto orderDto);
}