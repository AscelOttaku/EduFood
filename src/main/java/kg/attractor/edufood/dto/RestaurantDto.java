package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private Long id;

    @NotBlank
    private String name;

}
