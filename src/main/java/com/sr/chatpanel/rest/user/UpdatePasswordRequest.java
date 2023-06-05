package com.sr.chatpanel.rest.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
public class UpdatePasswordRequest {
    @Size(min=5, max=30,  message = "Password must be between 5 and 30.")
    private String password;
}
