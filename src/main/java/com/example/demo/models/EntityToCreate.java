package com.example.demo.models;

import javax.validation.constraints.NotBlank;

public class EntityToCreate {

    @NotBlank(message = "aa is mandatory")
    private final String content;

    public EntityToCreate(long id, String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
