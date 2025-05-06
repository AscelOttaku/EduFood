package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.mapper.UserMapper;
import kg.attractor.edufood.model.User;
import kg.attractor.edufood.repository.UserRepository;
import kg.attractor.edufood.security.MyUserDetails;
import kg.attractor.edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserDto userDto) {
        userRepository.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user= userRepository.findUserByEmail(email)
                .orElseThrow(()-> new NoSuchElementException("jkdsc"));
        return userMapper.mapToDto(user);
    }

    @Override
    public Boolean checkEmailForUniquness(String email) {
        return userRepository.findUserByEmail(email)
                .isPresent();
    }
    
    public MyUserDetails getAuthUserDetails() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
