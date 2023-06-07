package com.sr.chatpanel.websocket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InitChatResponse {

    private String chatToken;

    private String UUID;

    public String getChatToken() {
        return chatToken;
    }

    public void setChatToken(String chatToken) {
        this.chatToken = chatToken;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
