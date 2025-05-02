package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.OrderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    Long saveOrder(OrderDto orderDto);

    List<OrderDto> findAllOrders(int page, int size);
}
