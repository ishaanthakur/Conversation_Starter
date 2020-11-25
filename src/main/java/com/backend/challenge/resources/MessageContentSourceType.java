package com.backend.challenge.resources;

public enum MessageContentSourceType {
    YOUTUBE("youtube"), VIMEO("vimeo");

    private String formalName;

    MessageContentSourceType(String formalName) {
        this.formalName = formalName;
    }

    @Override
    public String toString() {
        return this.formalName;
    }
}
