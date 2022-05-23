package com.agustinvon.JibberJabber.model;

import com.sun.istack.NotNull;

public class PostForm {

    @NotNull
    private final String content;

    public PostForm(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
