package com.sr.chatpanel.rest.auth;

import com.sr.chatpanel.rest.auth.RegisterRequest;
import com.sr.chatpanel.rest.auth.AuthenticationRequest;
import com.sr.chatpanel.rest.auth.AuthenticationResponse;
import com.sr.chatpanel.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerSiteOwner(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerSiteOwner(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
