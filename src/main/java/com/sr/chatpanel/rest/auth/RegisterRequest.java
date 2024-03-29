package com.sr.chatpanel.rest.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30.")
    String password;
}
