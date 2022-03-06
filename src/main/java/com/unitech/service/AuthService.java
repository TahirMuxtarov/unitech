package com.unitech.service;

import com.unitech.error.CommonException;
import com.unitech.mapper.RegisterMapper;
import com.unitech.model.Role;
import com.unitech.model.dto.RegisterUserDto;
import com.unitech.model.entity.User;
import com.unitech.model.request.RegisterRequest;
import com.unitech.model.response.CommonResponse;
import com.unitech.repository.AuthRepository;
import com.unitech.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterMapper registerMapper;


    public CommonResponse createUser(RegisterRequest request) {
        RegisterUserDto registerUserDto = registerMapper.toRegisterDto(request);
        User user = authRepository.findByPin(registerUserDto.getPin()).orElse(null);

        if (Objects.nonNull(user)) {
            log.error("User with pin: {} already exist", request.getPin());
            throw new CommonException(ErrorMessage.USER_ALREADY_EXIST);
        }

        log.info("Creating user for pin: {}", request.getPin());
        user = createNewUser(registerUserDto);

        authRepository.save(user);

        return new CommonResponse();
    }

    private User createNewUser(RegisterUserDto request) {
        return User.builder()
                .createDate(LocalDate.now())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

    }
}
