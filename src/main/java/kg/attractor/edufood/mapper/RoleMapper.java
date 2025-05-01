package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.RoleDto;
import kg.attractor.edufood.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto mapToDto(Role role);

    Role mapToEntity(RoleDto roleDto);
}
