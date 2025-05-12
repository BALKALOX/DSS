package dss.service.impl;



import dss.dto.UserDto;
import dss.model.entity.Role;
import dss.model.entity.User;
import dss.repository.RoleRepository;
import dss.repository.UserRepository;
import dss.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setPhone_number(userDto.getPhone_number());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){

        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            var newRole = new Role();
            newRole.setName("ROLE_ADMIN");
            return roleRepository.save(newRole);
        }
        if (roleRepository.findByName("ROLE_USER") == null) {
            var newRole = new Role();
            newRole.setName("ROLE_USER");
            return roleRepository.save(newRole);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        
        userRepository.deleteById(id);
    }

    @Override
    public User changeRole(Long userId, String role){
        roleRepository.findAll().forEach(r -> System.out.println("ROLE IN DB: " + r.getName()));

        System.out.println(role);
        var user = userRepository.findById(userId).get();

        var newRole = roleRepository.findByName(role);
        System.out.println(newRole);
        List<Role> editable = new ArrayList<>(user.getRoles());
        editable.clear();
        editable.add(newRole);
        user.setRoles(editable);


        return userRepository.save(user);
    }



    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
