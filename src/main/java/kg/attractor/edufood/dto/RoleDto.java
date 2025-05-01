package kg.attractor.edufood.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long roleId;
    private String roleName;
}
