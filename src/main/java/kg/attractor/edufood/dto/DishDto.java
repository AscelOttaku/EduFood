package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class DishDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    private String name;

    @NotNull(message = "restaurant id cannot be null")
    @Positive(message = "restaurant id must be positive")
    private Long restaurantId;

    @NotNull
    @Positive
    private int price;
}
