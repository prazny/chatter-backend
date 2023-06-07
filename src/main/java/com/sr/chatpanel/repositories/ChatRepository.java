package com.sr.chatpanel.repositories;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.models.ChatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Collection<Chat> findAllByStatus(ChatStatus status);
    Chat findByChatToken(String token);
}
