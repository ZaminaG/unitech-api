package com.unibank.unitech.service;

import com.unibank.unitech.dao.repository.UserRepository;
import com.unibank.unitech.exception.AlreadyExistException;
import com.unibank.unitech.mapper.UserMapper;
import com.unibank.unitech.model.dto.AuthenticationToken;
import com.unibank.unitech.model.dto.SignInDto;
import com.unibank.unitech.model.dto.UserRegisterDto;
import com.unibank.unitech.security.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationToken signIn(SignInDto signInDto) {
        var authentication = authenticateUser(signInDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtTokenManager.generateToken(signInDto.getPin());
        log.info("User signed in successfully with PIN: {}", signInDto.getPin());
        return new AuthenticationToken(token);
    }

    public void signUp(UserRegisterDto userRegisterDto) {
        log.info("Attempting to sign up a new user with PIN: {}", userRegisterDto.getPin());
        throwIfUserAlreadyRegisteredByPin(userRegisterDto.getPin());
        saveUser(userRegisterDto);
        log.info("User signed up successfully with PIN: {}", userRegisterDto.getPin());
    }

    private Authentication authenticateUser(SignInDto signInDto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getPin(), signInDto.getPassword()));
    }

    private void throwIfUserAlreadyRegisteredByPin(String pin) {
        if (userRepository.existsByPin(pin)) {
            throw new AlreadyExistException("A user with the provided PIN already exists.");
        }
    }

    private void saveUser(UserRegisterDto userRegisterDto) {
        var userEntity = userMapper.toUserEntity(userRegisterDto);
        userEntity.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.save(userEntity);
    }
}
