package com.sr.chatpanel.rest.user;

import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
public class UpdatePasswordRequest {
    @Size(min=5,  message = "Password must be between 5 and 30.")
    private String password;
}
