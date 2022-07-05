package com.agustinvon.JibberJabber.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private String userID;

    @Column
    private LocalDateTime timestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", targetEntity = Reply.class)
    private List<Reply> replies = new ArrayList<>();


    public Post(String content, String userID, LocalDateTime timestamp) {
        this.content = content;
        this.userID = userID;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public String getUserID() {
        return userID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
