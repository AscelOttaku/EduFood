package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {
    private Long authorityId = 1L;

    private String name;
}
