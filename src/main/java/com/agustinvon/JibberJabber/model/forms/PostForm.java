package com.agustinvon.JibberJabber.model.forms;
import com.sun.istack.NotNull;

public class PostForm {

    @NotNull
    private String content;

    @NotNull
    private String username;

    public PostForm(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public PostForm() {
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
