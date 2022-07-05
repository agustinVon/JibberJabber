package com.agustinvon.JibberJabber.model.responses;

import java.io.Serializable;
import java.util.List;

public class FollowResponse implements Serializable {
    private List<Follow> followList;

    public FollowResponse(List<Follow> followList) {
        this.followList = followList;
    }

    public FollowResponse() {
    }

    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }
}
