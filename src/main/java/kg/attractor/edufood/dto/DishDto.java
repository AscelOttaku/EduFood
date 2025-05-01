package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public class DishDto {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long restaurantId;

    @NotNull
    @Positive
    private int price;
}
