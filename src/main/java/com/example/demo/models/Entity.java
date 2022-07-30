package com.example.demo.models;

public class Entity {

    private final long id;
    private final String content;

    public Entity(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
