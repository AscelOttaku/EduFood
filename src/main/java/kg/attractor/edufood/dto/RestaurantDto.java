package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public class RestaurantDto {
    private Long id;

    @NotBlank
    private String name;

    private List<DishDto> dishes;
}
