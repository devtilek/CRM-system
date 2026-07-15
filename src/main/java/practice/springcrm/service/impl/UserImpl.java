package practice.springcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.springcrm.dto.SignInRequest;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.User;
import practice.springcrm.mapper.UserMapper;
import practice.springcrm.repository.UserRepo;
import practice.springcrm.service.UserService;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDTO registerUser(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            throw new IllegalArgumentException("Пароли не совпадает");
        }

        if (userRepo.existsByEmail(signUpRequest.getEmail())){
            throw new IllegalArgumentException("Пользователь с таким Email уже сущиствует ");
        }

        if (userRepo.existsByUsername(signUpRequest.getUsername())){
            throw new IllegalArgumentException("Пользователь с таким Username уже сущиствует");
        }

        User user = userMapper.toEntity(signUpRequest);

        User savedUser = userRepo.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loginUser(SignInRequest signInRequest) {
        User user = userRepo.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Неверный Email или password"));

        if (!user.getPassword().equals(signInRequest.getPassword())){
            throw new IllegalArgumentException("Неверный Email или password");
        }

        return userMapper.toDTO(user);
    }
}
