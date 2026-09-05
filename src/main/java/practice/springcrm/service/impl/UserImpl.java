package practice.springcrm.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.springcrm.dto.JwtResponse;
import practice.springcrm.dto.SignInRequest;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.Role;
import practice.springcrm.entity.User;
import practice.springcrm.mapper.UserMapper;
import practice.springcrm.repository.UserRepo;
import practice.springcrm.security.JwtProvider;
import practice.springcrm.service.UserService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO registerUser(SignUpRequest signUpRequest) {
        String email = signUpRequest.getEmail().trim().toLowerCase(Locale.ROOT);
        String username = signUpRequest.getUsername().trim();

        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("A user with this email already exists");
        }

        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("A user with this username already exists");
        }

        User user = userMapper.toEntity(signUpRequest);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ROLE_STUDENT);

        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public JwtResponse loginUser(SignInRequest signInRequest) {
        String email = signInRequest.getEmail().trim().toLowerCase(Locale.ROOT);

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return new JwtResponse(jwtProvider.generateToken(user), user.getUsername());
    }

    @Override
    @Transactional
    public void changeUserRole(String email, Role role) {
        User user = userRepo.findByEmail(email.trim().toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setRole(role);
    }
}
