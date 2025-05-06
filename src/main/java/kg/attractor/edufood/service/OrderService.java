package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.OrderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    Long saveOrder();

    List<OrderDto> findAllOrders(int page, int size);
}
