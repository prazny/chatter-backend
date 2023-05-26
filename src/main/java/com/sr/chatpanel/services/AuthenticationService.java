package com.sr.chatpanel.services;

import com.sr.chatpanel.rest.auth.AuthenticationRequest;
import com.sr.chatpanel.rest.auth.AuthenticationResponse;
import com.sr.chatpanel.rest.auth.RegisterRequest;
import com.sr.chatpanel.config.auth.JwtService;
import com.sr.chatpanel.models.Role;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.repositories.UserRepository;
import com.sr.chatpanel.rest.user.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerSiteOwner(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.SITE_OWNER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void updatePassword(User user, UpdatePasswordRequest request) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);


        long expiresInMillis = jwtService.extractExpiration(jwtToken).getTime() - (new Date()).getTime();
        long expiresInSecs = expiresInMillis / 1000;

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .expiresIn(expiresInSecs)
                .build();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
