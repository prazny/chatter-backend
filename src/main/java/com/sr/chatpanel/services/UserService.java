package com.sr.chatpanel.services;

import com.sr.chatpanel.models.User;
import com.sr.chatpanel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public void updatePassword(User user, String password) {
        user.setPassword(password);
        repository.save(user);
    }
}
