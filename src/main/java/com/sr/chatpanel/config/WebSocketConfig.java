package com.sr.chatpanel.config;

import com.sr.chatpanel.config.auth.JwtService;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
@Component
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtService jwtService;
    private final UserService userService;
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/wssrv")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @EventListener(SessionConnectEvent.class)
    public void handleSessionConnected(SessionConnectEvent event) {
        Message<byte[]> eventMessage = event.getMessage();
        String token = getAuthorizationToken(eventMessage);
        if(token != null) {
            System.out.println("tokennnn: " + token);
            String email = jwtService.extractUsername(token.replace("Bearer", "").trim());
            String sessionID = SimpAttributesContextHolder.currentAttributes().getSessionId();
            userService.saveWsSession(email, sessionID);
        }
    }

    private String getAuthorizationToken(Message<byte[]> message) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        List<String> authorization = Optional.of(headerAccessor)
                .map($ -> $.getNativeHeader(WebSocketHttpHeaders.AUTHORIZATION))
                .orElse(Collections.emptyList());

        return authorization.stream()
                .findFirst()
                .orElse(null);
    }


}