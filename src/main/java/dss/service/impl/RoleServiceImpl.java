package dss.service.impl;

import dss.model.entity.Role;
import dss.repository.RoleRepository;
import dss.repository.UserRepository;
import dss.service.RoleService;
import dss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }


    @Override
    public Role createRole(String role) {

        var roleEntity = new Role();
        roleEntity.setName(role);
        return repository.save(roleEntity);
    }

    @Override
    public Role updateRole(Long id, String roleName) {
        var role = repository.findById(id).orElse(null);
        role.setName(roleName);
        return repository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteRole(Long id) {
        repository.deleteById(id);
    }
}
