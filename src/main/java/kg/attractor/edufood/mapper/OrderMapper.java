package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DishMapper.class, UserMapper.class})
public interface OrderMapper {

    OrderDto mapToDto(Order order);

    Order mapToEntity(OrderDto orderDto);
}