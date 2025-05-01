package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.mapper.util.MapperUtil;
import kg.attractor.edufood.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorityMapper.class, MapperUtil.class})
public interface UserMapper {

    UserDto mapToDto(User user);

    @Mapping(target = "password", qualifiedByName = "encodePassword")
    User mapToEntity(UserDto user);
}
