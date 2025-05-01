package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public class RestaurantDto {
    private Long id;
    @NotBlank
    private String name;
}
