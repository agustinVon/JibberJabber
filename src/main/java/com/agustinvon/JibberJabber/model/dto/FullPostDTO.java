package com.agustinvon.JibberJabber.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FullPostDTO {
    private UUID id;
    private final String text;
    private final LocalDateTime localDateTime;
    private final UserDTO user;
    private final List<ReplyDTO> thread;

    public FullPostDTO(UUID id, String text, LocalDateTime localDateTime, List<ReplyDTO> replies, UserDTO user) {
        this.text = text;
        this.localDateTime = localDateTime;
        this.id = id;
        this.user = user;
        this.thread = replies;
    }

    public String getText() {
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

    public List<ReplyDTO> getReplies() {
        return thread;
    }
}
