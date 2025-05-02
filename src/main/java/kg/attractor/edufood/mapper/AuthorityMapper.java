package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.AuthorityDto;
import kg.attractor.edufood.model.Authority;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMapper {

    public AuthorityDto mapToDto(Authority authority) {

        AuthorityDto dto = new AuthorityDto();
        dto.setAuthorityId(authority.getAuthorityId());
        dto.setName(authority.getName());
        return dto;
    }

    public Authority mapToEntity(AuthorityDto dto) {

        Authority authority = new Authority();
        authority.setAuthorityId(dto.getAuthorityId());
        authority.setName(dto.getName());
        return authority;
    }
}

