package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.mapper.DishMapper;
import kg.attractor.edufood.mapper.OrderMapper;
import kg.attractor.edufood.model.OrderDish;
import kg.attractor.edufood.model.Orders;
import kg.attractor.edufood.repository.OrderDishRepository;
import kg.attractor.edufood.repository.OrderRepository;
import kg.attractor.edufood.service.AuthService;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AuthService authService;
    private final BucketService bucketService;
    private final DishMapper dishMapper;
    private final OrderDishRepository orderDishRepository;

    @Transactional
    @Override
    public Long saveOrder() {
        Map<DishDto, Integer> dishes = bucketService.getBucket();

        OrderDto orderDto = OrderDto.builder()
                .dishes(dishes)
                .build();

        if (orderDto.getDishes().isEmpty())
            throw new IllegalStateException("order cannot be performed without dishes");

        orderDto.setUser(authService.getAuthUser());

        Orders order = orderRepository.save(orderMapper.mapToEntity(orderDto));

        dishes.forEach((key, value) -> {
            OrderDish orderDish = new OrderDish();
            orderDish.setOrders(order);
            orderDish.setDish(dishMapper.mapToEntity(key));
            orderDish.setQuantity(value);
            orderDishRepository.save(orderDish);
        });

        return order.getId();
    }

    @Override
    public List<OrderDto> findAllOrders(int page, int size) {
        UserDto userDto = authService.getAuthUser();

        return orderRepository.findOrdersByUserId(userDto.getId(), PageRequest.of(page, size))
                .stream()
                .map(orderMapper::mapToDto)
                .sorted(Comparator.comparing(orderDto -> orderDto.getDishes().values()
                        .stream()
                        .reduce(Integer::sum)
                        .orElseThrow(() -> new IllegalStateException("order cannot be performed without dishes"))))
                .toList();
    }
}
