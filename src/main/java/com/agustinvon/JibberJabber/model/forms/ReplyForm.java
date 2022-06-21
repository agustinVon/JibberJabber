package com.agustinvon.JibberJabber.model.forms;

import com.sun.istack.NotNull;

public class ReplyForm {

    @NotNull
    private String content;

    @NotNull private String username;

    public ReplyForm(String content, String username) {
        this.username = username;
        this.content = content;
    }

    public ReplyForm() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}