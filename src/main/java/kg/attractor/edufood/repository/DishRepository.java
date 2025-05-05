package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    Page<Dish> findDishByRestaurantId(Long restaurantId, Pageable pageable);

    List<Dish> findDishByRestaurantId(Long restaurantId);
}
