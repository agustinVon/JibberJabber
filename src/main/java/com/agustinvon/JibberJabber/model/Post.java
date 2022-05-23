package com.agustinvon.JibberJabber.model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private double id;
    private String content;
    private String username;
    private LocalDateTime timestamp;

    public Post(String content, String username, LocalDateTime timestamp) {
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    public Post() {
    }
}
