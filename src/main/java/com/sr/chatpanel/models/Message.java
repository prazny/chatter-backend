package com.sr.chatpanel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "human_message")
public class Message  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String sendDate;

    @Enumerated(EnumType.STRING)
    private MessageSender senderType;

    @NotBlank
    private String senderName;

    @NotBlank
    private String chatToken;

    private String content;

}
