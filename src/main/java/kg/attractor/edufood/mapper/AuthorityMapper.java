package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.AuthorityDto;
import kg.attractor.edufood.model.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    AuthorityDto mapToDto(Authority authority);

    @Mapping(target = "authorityId", constant = "1L")
    Authority mapToEntity(AuthorityDto authorityDto);
}
