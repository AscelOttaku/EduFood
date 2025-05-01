package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.AuthorityDto;
import kg.attractor.edufood.model.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    AuthorityDto mapToDto(Authority authority);

    Authority mapToEntity(AuthorityDto authorityDto);
}
