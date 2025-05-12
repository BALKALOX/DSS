package dss.controller.rest;

import dss.model.entity.User;
import dss.repository.RoleRepository;
import dss.repository.UserRepository;
import dss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<User> changeRole(@PathVariable Long id, @RequestBody String role) {
        return ResponseEntity.ok(userService.changeRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, Authentication authentication) {
        var authUser = userService.findUserByEmail(authentication.getName());
        if (authUser.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
