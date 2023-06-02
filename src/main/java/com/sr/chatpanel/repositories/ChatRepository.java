package com.sr.chatpanel.repositories;

import com.sr.chatpanel.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
