package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.OrderDish;
import kg.attractor.edufood.model.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    public OrderDto mapToDto(Orders order) {
        Map<DishDto, Integer> orders = order.getOrderDishes().stream()
                .collect(Collectors.toMap(
                        orderDish -> dishMapper.mapToDto(orderDish.getDish()),
                        OrderDish::getQuantity
                ));

        return OrderDto.builder()
                .id(order.getId())
                .user(userMapper.mapToDto(order.getUser()))
                .dishes(orders)
                .build();
    }
    public Orders mapToEntity(OrderDto dto) {
        Orders orders = new Orders();
        orders.setId(dto.getId());
        orders.setUser(userMapper.mapToEntity(dto.getUser()));
        return orders;
    }
}
