package com.sr.chatpanel.services;

import com.sr.chatpanel.models.Provider;
import com.sr.chatpanel.models.Role;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.repositories.UserRepository;
import com.sr.chatpanel.rest.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void updatePassword(User user, String password) {
        user.setPassword(password);
        repository.save(user);
    }

    public void processOAuthPostLogin(OAuth2User user) {
        Optional<User> existUser = repository.findByEmail(user.getAttribute("email"));

        if (existUser.isEmpty()) {
            var newUser = User.builder()
                    .firstName(user.getAttribute("given_name"))
                    .lastName(user.getAttribute("family_name"))
                    .email(user.getAttribute("email"))
                    .password(passwordEncoder.encode((user.getAttribute("sub"))))
                    .provider(Provider.GOOGLE)
                    .role(Role.SITE_OWNER)
                    .build();
            repository.save(newUser);
        }
    }

    public void saveWsSession(String mail, String session) {
        User user = repository.findByEmail(mail).orElse(null);
        if(user != null) {
            user.setWsSessionId(session);
            repository.save(user);
        }
    }

}
