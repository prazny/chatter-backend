package com.sr.chatpanel.rest.user;

import com.sr.chatpanel.models.User;
import com.sr.chatpanel.services.AuthenticationService;
import com.sr.chatpanel.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping("/profile")
    public User getProfile() {
        return authenticationService.getCurrentUser();
    }

    @PatchMapping("")
    public void updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        User user = authenticationService.getCurrentUser();
        authenticationService.updatePassword(user, updatePasswordRequest);
    }
}
