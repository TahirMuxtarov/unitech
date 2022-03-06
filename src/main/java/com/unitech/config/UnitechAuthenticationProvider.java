package com.unitech.config;

import com.unitech.model.entity.User;
import com.unitech.repository.AuthRepository;
import com.unitech.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UnitechAuthenticationProvider implements AuthenticationProvider {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String pin = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = authRepository.findByPin(pin).orElse(null);
        if (Objects.nonNull(user)) {
            if (passwordEncoder.matches(pwd, user.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
                return new UsernamePasswordAuthenticationToken(pin, pwd, authorities);
            } else {
                throw new BadCredentialsException(ErrorMessage.INVALID_PASSWORD.getMessage());
            }
        }else {
            throw new BadCredentialsException(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
