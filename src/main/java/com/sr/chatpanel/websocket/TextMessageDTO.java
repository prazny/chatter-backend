package com.sr.chatpanel.websocket;

import com.sr.chatpanel.models.MessageSender;

public class TextMessageDTO {
    private String message;

    private String userTo;

    private String senderName;

    private MessageSender senderType;

    private String date;

    private String chatToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MessageSender getSenderType() {
        return senderType;
    }

    public void setSenderType(MessageSender senderType) {
        this.senderType = senderType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getChatToken() {
        return chatToken;
    }

    public void setChatToken(String chatToken) {
        this.chatToken = chatToken;
    }
}
