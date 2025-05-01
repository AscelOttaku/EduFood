package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.RoleDto;
import kg.attractor.edufood.model.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto mapToDto(Authority authority);

    Authority mapToEntity(RoleDto roleDto);
}
