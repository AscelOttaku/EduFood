package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.mapper.OrderMapper;
import kg.attractor.edufood.repository.OrderRepository;
import kg.attractor.edufood.service.AuthService;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DishService dishService;
    private final AuthService authService;

    @Transactional
    @Override
    public Long saveOrder(OrderDto orderDto) {
        orderDto.getDishes().forEach(dish -> dishService.findDishById(dish.getId()));
        orderDto.setUser(authService.getAuthUser());

        return orderRepository.save(orderMapper.mapToEntity(orderDto))
                .getId();
    }

    @Override
    public List<OrderDto> findAllOrders(int page, int size) {
        UserDto userDto = authService.getAuthUser();

        return orderRepository.findOrdersByUserId(userDto.getId(), PageRequest.of(page, size))
                .stream()
                .map(orderMapper::mapToDto)
                .toList();
    }
}
