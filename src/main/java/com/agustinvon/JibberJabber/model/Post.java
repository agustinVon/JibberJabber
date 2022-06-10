package com.agustinvon.JibberJabber.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "post")
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String content;

    @Column
    private String username;

    @Column
    private LocalDateTime timestamp;

    public Post(String content, String username, LocalDateTime timestamp) {
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    public Post() {
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
