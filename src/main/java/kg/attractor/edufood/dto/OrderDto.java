package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class OrderDto {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long dishId;
}
