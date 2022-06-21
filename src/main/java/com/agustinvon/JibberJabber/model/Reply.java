package com.agustinvon.JibberJabber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String username;

    @Column
    private String userID;

    @Column
    private String content;

    @Column
    private LocalDateTime dateTime;

    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    public Reply(String username, String userID, String content, LocalDateTime dateTime, Post post) {
        this.username = username;
        this.userID = userID;
        this.content = content;
        this.dateTime = dateTime;
        this.post = post;
    }

    public Reply() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Post getPost() {
        return post;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
