package kg.attractor.edufood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;

    private UserDto user;

    private Map<RestaurantDto, DishDto> dishes;
}
