package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
    UserDto findUserByEmail(String email);
    Boolean checkUserInDB(String email);
}
