package kg.attractor.edufood.dto;

import jakarta.validation.constraints.*;
import kg.attractor.edufood.customizesValidators.ValidEmail;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "email should not be blank")
    @Email(message = "email format is incorrect")
    @ValidEmail(message = "email already exists in app")
    private String email;

    @NotBlank(message = "name should bot be blank")
    @Size(max = 20, min = 3, message = "name size should be between (3 - 20)")
    private String name;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 4, max = 150, message = "Length must be >= 4 and <= 150")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one uppercase letter, one number")
    private String password;

    private RoleDto role;
}
