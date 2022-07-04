package com.agustinvon.JibberJabber.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostDTO {
    private UUID id;
    private final String text;
    private final LocalDateTime localDateTime;
    private final UserDTO user;

    public PostDTO(UUID id, String content, LocalDateTime localDateTime, UserDTO user) {
        this.text = content;
        this.localDateTime = localDateTime;
        this.id = id;
        this.user = user;
    }

    public String getContent() {
        return text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public UUID getId() {
        return id;
    }

    public UserDTO getUser() {
        return user;
    }
}
