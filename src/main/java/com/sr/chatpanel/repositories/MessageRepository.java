package com.sr.chatpanel.repositories;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.models.ChatStatus;
import com.sr.chatpanel.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Collection<Message> findAllByChatToken(String chatToken);
}
