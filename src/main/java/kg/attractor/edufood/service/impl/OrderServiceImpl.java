package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.mapper.OrderMapper;
import kg.attractor.edufood.repository.OrderRepository;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DishService dishService;

    @Transactional
    @Override
    public Long saveOrder(OrderDto orderDto) {
        dishService.findDishById(orderDto.getDishId());

        return orderRepository.save(orderMapper.mapToEntity(orderDto))
                .getId();
    }
}
