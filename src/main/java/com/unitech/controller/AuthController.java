package com.unitech.controller;

import com.unitech.model.request.LoginRequest;
import com.unitech.model.request.RegisterRequest;
import com.unitech.model.response.CommonResponse;
import com.unitech.model.response.LoginResponse;
import com.unitech.service.AuthService;
import com.unitech.util.ErrorMessage;
import com.unitech.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public CommonResponse createUser(@RequestBody @Valid RegisterRequest request) {
        return authService.createUser(request);
    }

    @PostMapping("/login")
    public LoginResponse generateToken(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getPin(), loginRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception(ErrorMessage.INVALID_CREDENTIALS.getMessage());
        }
        return new LoginResponse(jwtUtil.generateToken(loginRequest.getPin()));
    }
}
