package com.agustinvon.JibberJabber.model.forms;
import com.sun.istack.NotNull;

public class PostForm {

    @NotNull
    private String content;

    public PostForm(String content) {
        this.content = content;
    }

    public PostForm() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
