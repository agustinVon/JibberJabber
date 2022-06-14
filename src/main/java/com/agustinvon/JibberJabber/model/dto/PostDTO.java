package com.agustinvon.JibberJabber.model.dto;

import java.time.LocalDateTime;

public class PostDTO {
    private final String content;
    private final String username;
    private final LocalDateTime localDateTime;

    public PostDTO(String content, String username, LocalDateTime localDateTime) {
        this.content = content;
        this.username = username;
        this.localDateTime = localDateTime;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
