package com.agustinvon.JibberJabber.model.forms;

import com.sun.istack.NotNull;

public class ReplyForm {

    @NotNull
    private String content;

    public ReplyForm(String content) {
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
}