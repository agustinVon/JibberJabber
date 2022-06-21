package com.agustinvon.JibberJabber.model.responses;
import java.io.Serializable;

public class Follow implements Serializable {

    private String id;

    private String mainUser;

    private String followedUser;

    public Follow(String mainUser, String followedUser) {
        this.mainUser = mainUser;
        this.followedUser = followedUser;
    }

    public Follow() {
    }

    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }

    public void setFollowedUser(String followedUser) {
        this.followedUser = followedUser;
    }

    public String getId() {
        return id;
    }

    public String getMainUser() {
        return mainUser;
    }

    public String getFollowedUser() {
        return followedUser;
    }
}
