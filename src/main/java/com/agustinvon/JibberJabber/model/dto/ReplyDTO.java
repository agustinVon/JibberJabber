package com.agustinvon.JibberJabber.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReplyDTO {
    private UUID id;
    private final String text;
    private final LocalDateTime localDateTime;
    private final UserDTO user;

    public ReplyDTO(UUID id, String content, LocalDateTime localDateTime, UserDTO user) {
        this.id = id;
        this.text = content;
        this.localDateTime = localDateTime;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public UserDTO getUser() {
        return user;
    }
}
