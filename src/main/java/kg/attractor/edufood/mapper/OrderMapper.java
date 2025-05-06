package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Order;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final DishMapper dishMapper;
    private final RestaurantMapper restaurantMapper;
    private final DishService dishService;

    public OrderDto mapToDto(Order order) {
        Map<DishDto, Integer> orders = order.getDishes().stream()
                .map(dishMapper::mapToDto)
                .collect(Collectors.toMap(
                        Function.identity(),
                        dto -> 1,
                        Integer::sum
                ));

        return OrderDto.builder()
                .id(order.getId())
                .user(userMapper.mapToDto(order.getUser()))
                .restaurantDishes(orders)
                .build();
    }

    public Order mapToEntity(OrderDto dto) {
        List<Dish> dishes=new ArrayList<>();
        for (Map.Entry<DishDto, Integer> entry : dto.getRestaurantDishes().entrySet()) {
            Dish dish = dishMapper.mapToEntity(entry.getKey());
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                dishes.add(dish);
            }
        }

        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userMapper.mapToEntity(dto.getUser()));
        order.setDishes(dishes);

        return order;
    }



}
