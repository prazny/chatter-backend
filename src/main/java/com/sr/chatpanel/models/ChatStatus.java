package com.sr.chatpanel.models;

public enum ChatStatus {
    BACKGROUND, // talk with bot, next -> NEW
    NEW, // waiting for assign to consultant, next -> IN_PROGRESS
    IN_PROGRESS, // talk with consultant, next -> BACKGROUND
    ENDED // unused now
}
