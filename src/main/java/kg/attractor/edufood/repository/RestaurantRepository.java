package kg.attractor.edufood.repository;

import kg.attractor.edufood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantById(Long restaurantId);

    Optional<Restaurant> findRestaurantByNameContainingIgnoreCase(String restaurantName);
}
