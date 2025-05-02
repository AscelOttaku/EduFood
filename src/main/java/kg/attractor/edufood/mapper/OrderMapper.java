package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    public OrderDto mapToDto(Order order) {

        List<DishDto> dishDtos = order.getDishes().stream().map(dishMapper::mapToDto).collect(Collectors.toList());

        return OrderDto.builder()
                .id(order.getId())
                .user(userMapper.mapToDto(order.getUser()))
                .dishes(dishDtos)
                .build();
    }

    public Order mapToEntity(OrderDto dto) {

        List<Dish> dishes = dto.getDishes().stream().map(dishMapper::mapToEntity).toList();

        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userMapper.mapToEntity(dto.getUser()));


        order.setDishes(dishes);

        return order;
    }
}
