package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.OrderDto;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    Long saveOrder(OrderDto orderDto);
}
