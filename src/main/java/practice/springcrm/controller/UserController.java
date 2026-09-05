package practice.springcrm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practice.springcrm.dto.JwtResponse;
import practice.springcrm.dto.SignInRequest;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.Role;
import practice.springcrm.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        UserDTO registeredUser = userService.registerUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userService.loginUser(signInRequest));
    }

    @GetMapping("/admin-test")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Hello, Admin!");
    }

    @PutMapping("/roles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> changeRole(
            @RequestParam String email,
            @RequestParam Role role
    ) {
        userService.changeUserRole(email, role);
        return ResponseEntity.noContent().build();
    }
}
