package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
}
