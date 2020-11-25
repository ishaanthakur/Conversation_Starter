package com.asapp.backend.challenge.controller;

import com.backend.challenge.resources.HealthResource;
import com.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class HealthController {
    //Already implemented
    public static Route check = (Request req, Response rep) -> {
        // TODO Allow messages of the logged user to be stored
        return JSONUtil.dataToJson(new HealthResource("ok"));
    };
}
