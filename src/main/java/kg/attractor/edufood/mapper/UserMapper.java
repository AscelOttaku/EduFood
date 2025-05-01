package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserDto mapToDto(User user);

    User mapToEntity(UserDto user);
}
