package dss.service;

import dss.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();

    Role createRole(String role);

    Role updateRole(Long id, String roleName);

    Role getRole(Long id);

    void deleteRole(Long id);
}
