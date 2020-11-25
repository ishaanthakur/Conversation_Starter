package com.backend.challenge.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder  @Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseResource {

    private int id;

    private String token;

}
