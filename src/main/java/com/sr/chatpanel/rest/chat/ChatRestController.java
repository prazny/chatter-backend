package com.sr.chatpanel.rest.chat;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.models.ChatStatus;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.rest.exceptions.EntityNotFound;
import com.sr.chatpanel.services.ChatService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    @GetMapping("/")
    public ResponseEntity<Object> getChatsWithStatus(ChatStatus status, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(chatService.getMany(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> get(@PathVariable int id) throws EntityNotFound {
        return new ResponseEntity<>(chatService.get(id), HttpStatus.OK);
    }
}
