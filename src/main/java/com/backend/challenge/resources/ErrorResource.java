package com.backend.challenge.resources;

import com.backend.challenge.utils.JSONUtil;
import lombok.Builder;
import lombok.Getter;
import spark.Response;

@Builder @Getter
public class ErrorResource {
    private String message;
    private int status;

    public static String getError(Response resp, int status, String message) {
        resp.status(status);
        return JSONUtil.dataToJson(ErrorResource.builder().message(message).status(status).build());
    }
}
