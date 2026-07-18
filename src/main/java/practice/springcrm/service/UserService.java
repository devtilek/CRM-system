package practice.springcrm.service;

import practice.springcrm.dto.JwtResponse;
import practice.springcrm.dto.SignInRequest;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.Role;

public interface UserService {
    UserDTO registerUser(SignUpRequest signUpRequest);

    JwtResponse loginUser(SignInRequest signInRequest);

    void changeUserRole(String email, Role role);
}
