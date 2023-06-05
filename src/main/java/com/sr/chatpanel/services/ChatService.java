package com.sr.chatpanel.services;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.models.ChatStatus;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.repositories.ChatRepository;
import com.sr.chatpanel.rest.exceptions.ActionImpossible;
import com.sr.chatpanel.rest.exceptions.EntityNotFound;
import com.sr.chatpanel.utils.StringGenerator;
import com.sr.chatpanel.websocket.chat.InitChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

   // @Value("${app.chat.token.size}")
    private String tokenSize = "50";

    public List<Chat> getMany() {
        return (List<Chat>) chatRepository.findAll();
    }

    public List<Chat> getManyWithStatus(ChatStatus status) {
        return (List<Chat>) chatRepository.findAllByStatus(status);
    }

    public Chat get(int id) throws EntityNotFound {
        return chatRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public void assign(Chat chat, User user) throws ActionImpossible {
        if(!chat.getStatus().equals(ChatStatus.NEW)) throw new ActionImpossible();
        chat.setStatus(ChatStatus.IN_PROGRESS);
        chat.setUser(user);
        chatRepository.save(chat);
    }

    public Chat init(InitChatRequest chatRequest, String sessionId) {
        Chat chat = new Chat();
        chat.setStatus(ChatStatus.BACKGROUND);
        chat.setCustomerName(chatRequest.getNick());
        chat.setChatToken(StringGenerator.getRandomAlphaString(Integer.parseInt(tokenSize)));
        chat.setCustomerSessionId(sessionId);

        this.chatRepository.save(chat);
        return chat;
    }
}
