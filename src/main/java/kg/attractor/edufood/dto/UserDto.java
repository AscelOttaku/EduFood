package kg.attractor.edufood.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "email should not be blank")
    @Email(message = "email format is incorrect")
    private String email;

    @NotBlank(message = "name should bot be blank")
    @Size(max = 20, min = 3, message = "name size should be between (3 - 20)")
    private String name;

    @NotNull(message = "age cannot be null")
    @Min(value = 18, message = "min age should be 18")
    @Max(value = 145, message = "max age cannot be bigger then 145")
    private Integer age;

    @NotBlank(message = "{blank_message}")
    @Size(min = 13, max = 13,
            message = "{phone_number_size_message}"
    )
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s]+$",
            message = "{phone_number_pattern_message}"
    )
    private String phoneNumber;

    private String avatar;
    private RoleDto role;
}
