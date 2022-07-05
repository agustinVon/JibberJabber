package com.agustinvon.JibberJabber.model.dto;

public class UserDTO {
    private final String id;
    private final String displayName;
    private final String username;
    private final String avatar;

    public UserDTO(String id, String displayName, String username, String avatar) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }
}
