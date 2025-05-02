package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.mapper.UserMapper;
import kg.attractor.edufood.security.MyUserDetails;
import kg.attractor.edufood.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;

    @Override
    public MyUserDetails getAuthUserDetails() {
        return (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public UserDto getAuthUser() {
        return userMapper.mapToDto(getAuthUserDetails().user());
    }
}
