package com.agustinvon.JibberJabber.model.responses;

public class UserReply {
    private String id;
    private String username;
    private String firstName;

    public UserReply(String id, String username, String firstName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }
}
