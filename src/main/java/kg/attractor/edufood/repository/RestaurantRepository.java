package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantById(Long restaurantId);

    Page<Restaurant> findRestaurantByNameContainingIgnoreCase(String restaurantName, Pageable pageable);
}
