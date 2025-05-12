package dss.controller.rest;

import dss.security.jwt.JwtService;
import dss.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getAuthenticatedUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.findUserByEmail(authentication.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(Collections.singletonMap("token", jwt));
    }

    static class AuthRequest {
        public String email;
        public String password;

        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
    }
}
