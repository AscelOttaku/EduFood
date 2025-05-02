package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantDto {
    private Long id;

    @NotBlank
    private String name;

    private PageHolder<DishDto> dishes;
}
