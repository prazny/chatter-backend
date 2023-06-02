package com.sr.chatpanel.websocket;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/customer/initCHat")
    public Chat greeting(
            Chat chat,
            @Header("simpSessionId") String sessionId
    ) {
        return chatService.init(chat, sessionId);
    }
}
