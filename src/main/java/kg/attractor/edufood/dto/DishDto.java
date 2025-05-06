package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
    private RestaurantDto restaurant;

    @NotNull
    @Positive
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dishDto = (DishDto) o;
        return Objects.equals(id, dishDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
