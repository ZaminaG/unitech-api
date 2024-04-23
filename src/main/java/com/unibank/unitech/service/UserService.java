package com.unibank.unitech.service;

import com.unibank.unitech.dao.entity.UserEntity;
import com.unibank.unitech.dao.repository.UserRepository;
import com.unibank.unitech.exception.ForbiddenException;
import com.unibank.unitech.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        var user = userRepository.findByPin(pin).orElseThrow(
                () -> new UsernameNotFoundException("User not found with the provided PIN."));
        return new User(user.getPin(), user.getPassword(), new ArrayList<>());
    }

    public UserEntity getCurrentUser() {
        String currentUserPin = getCurrentUserPin();
        return userRepository.findByPin(currentUserPin)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    private String getCurrentUserPin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            throw new ForbiddenException("No authenticated user found");
        }
        return authentication.getName();
    }
}
