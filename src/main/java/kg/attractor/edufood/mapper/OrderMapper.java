package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Order;
import kg.attractor.edufood.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto mapToDto(Order order);

    Order mapToEntity(OrderDto orderDto);
}