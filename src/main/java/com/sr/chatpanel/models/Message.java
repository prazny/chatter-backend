package com.sr.chatpanel.models;

import jakarta.persistence.*;
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
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date sendDate;

    @Temporal(TemporalType.TIME)
    private Time sendTime;

    private MessageSender sender;

    //private User consultant;

    private String content;



}
