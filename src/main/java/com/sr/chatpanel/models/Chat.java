package com.sr.chatpanel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChatStatus status;

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerSessionId;

    @NotBlank
    private String customerUUID;

    private String chatToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
