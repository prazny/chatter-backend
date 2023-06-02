package com.sr.chatpanel.services;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.repositories.ChatRepository;
import com.sr.chatpanel.utils.StringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Value("${app.chat.token.size}")
    private String tokenSize;

    public Chat init(Chat chat, String sessionId) {
        chat.setChatToken(StringGenerator.getRandomAlphaString(Integer.parseInt(tokenSize)));
        chat.setCustomerSessionId(sessionId);
        this.chatRepository.save(chat);
        return chat;
    }
}
