package practice.springcrm.service;

import practice.springcrm.dto.SignInRequest;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(SignUpRequest signUpRequest);

    UserDTO loginUser(SignInRequest signInRequest);
}
