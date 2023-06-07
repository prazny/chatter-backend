package com.sr.chatpanel.services;

import com.sr.chatpanel.models.Message;
import com.sr.chatpanel.repositories.MessageRepository;
import com.sr.chatpanel.rest.exceptions.EntityNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> getMany() {
        return (List<Message>) messageRepository.findAll();
    }

    public List<Message> getManyWithChatToken(String chatToken) {
        return (List<Message>) messageRepository.findAllByChatToken(chatToken);
    }

    public Message get(int id) throws EntityNotFound {
        return messageRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public void add(Message message){
        messageRepository.save(message);
    }

}
