package dss.service;



import dss.dto.UserDto;
import dss.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    List<UserDto> findAllUsers();
}