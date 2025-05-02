package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.AuthorityDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.model.Authority;
import kg.attractor.edufood.model.User;
import kg.attractor.edufood.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityMapper authorityMapper;
    private final AuthorityService authorityService;

    public UserDto mapToDto(User user) {

        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .authority(authorityMapper.mapToDto(user.getAuthority()))
                .build();
    }

    public User mapToEntity(UserDto dto) {

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAuthority(authorityMapper.mapToEntity(authorityService.getAuthorityById(1L)));

        return user;
    }
}
