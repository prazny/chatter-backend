package com.sr.chatpanel.websocket.chat;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitChatRequest {
    @NotBlank
    private String nick;

}
