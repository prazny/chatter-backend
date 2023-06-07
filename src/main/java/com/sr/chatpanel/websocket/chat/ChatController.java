package com.sr.chatpanel.websocket.chat;

import com.sr.chatpanel.models.Chat;
import com.sr.chatpanel.models.Message;
import com.sr.chatpanel.models.MessageSender;
import com.sr.chatpanel.services.ChatService;
import com.sr.chatpanel.services.MessageService;
import com.sr.chatpanel.websocket.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    private final MessageService messageService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/customer/initChat")
    public Chat init(
            InitChatRequest chat,
            Principal user,
            @Header("simpSessionId") String sessionId
    ) {
        Chat new_chat = chatService.init(chat, user, sessionId);
        simpMessagingTemplate.convertAndSendToUser(new_chat.getCustomerUUID(),"/customer/token",
               new_chat.getChatToken());
        return new_chat;
    }

    @MessageMapping("/customer/reloadChat")
    public ResponseEntity<Void> reloadChat(
            String chatToken,
            Principal user,
            @Header("simpSessionId") String sessionId
    ) {
        List<Message> messages = messageService.getManyWithChatToken(chatToken);
        chatService.updateUUID(chatToken, user.getName());
        simpMessagingTemplate.convertAndSendToUser(user.getName(),"/customer/reload", messages);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/customer/initConsul")
    public ResponseEntity<Void>  initConsul(
            TextMessageDTO textMessageDTO,
            Principal user,
            @Header("simpSessionId") String sessionId
    ) {
        simpMessagingTemplate.convertAndSendToUser(textMessageDTO.getUserTo(),"/customer/initCons", user.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/customer/sendText")
    public void sendText(String content) {
        System.out.println(content);

    }

    @MessageMapping("/customer/send")
    public ResponseEntity<Void> sendMessage(@Payload TextMessageDTO textMessageDTO, @Header("simpSessionId") String sessionId) {
        simpMessagingTemplate.convertAndSendToUser(textMessageDTO.getUserTo(),"/customer/message",
                new TextMessageResponse(textMessageDTO.getMessage(), textMessageDTO.getDate()));

        System.out.println(textMessageDTO.getSenderName());

        Message message = Message.builder()
                                    .senderName(textMessageDTO.getSenderName())
                                    .content(textMessageDTO.getMessage())
                                    .chatToken(textMessageDTO.getChatToken())
                                    .sendDate(textMessageDTO.getDate())
                                    .senderType(textMessageDTO.getSenderType())
                                    .build();

        messageService.add(message);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
