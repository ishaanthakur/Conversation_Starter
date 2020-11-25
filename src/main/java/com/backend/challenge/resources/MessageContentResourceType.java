package com.backend.challenge.resources;

public enum MessageContentResourceType {
    TEXT("text"), IMAGE("image"), VIDEO("video");

    private String formalName;

    MessageContentResourceType(String formalName) {
        this.formalName = formalName;
    }

    @Override
    public String toString() {
        return this.formalName;
    }
}
