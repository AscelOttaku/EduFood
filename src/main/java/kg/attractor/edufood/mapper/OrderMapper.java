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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final DishMapper dishMapper;
    private final RestaurantMapper restaurantMapper;
    private final DishService dishService;

    public OrderDto mapToDto(Order order) {

        List<RestaurantDto> restaurantDtos = order.getRestaurants().stream()
                .map(restaurantMapper::mapToDto)
                .toList();

        Map<RestaurantDto, List<DishDto>> restaurantDishes = restaurantDtos
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        restaurantDto -> dishService.findDishByRestaurantId(restaurantDto.getId())
                ));

        return OrderDto.builder()
                .id(order.getId())
                .user(userMapper.mapToDto(order.getUser()))
                .restaurantDishes(restaurantDishes)
                .build();
    }

    public Order mapToEntity(OrderDto dto) {

        List<Restaurant> restaurantDtos = dto.getRestaurantDishes()
                .keySet()
                .stream()
                .map(restaurantMapper::mapToEntity)
                .toList();

        List<Dish> dishes = dto.getRestaurantDishes()
                .values()
                .stream()
                .flatMap(getDishes -> getDishes.stream()
                        .map(dishMapper::mapToEntity))
                .toList();

        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userMapper.mapToEntity(dto.getUser()));

        order.setRestaurants(restaurantDtos);
        order.setDishes(dishes);

        return order;
    }
}
