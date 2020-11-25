package com.backend.challenge.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageContentResource {

    private MessageContentResourceType type;
    private String text;
    private String url;
    private Integer height;
    private Integer width;
    private MessageContentSourceType source;
}
