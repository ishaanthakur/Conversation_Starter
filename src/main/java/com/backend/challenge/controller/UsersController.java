package com.backend.challenge.controller;

import java.util.*;

import com.backend.challenge.BasicModule;
import com.backend.challenge.resources.ErrorResource;
import com.backend.challenge.resources.LoginResponseResource;
import com.backend.challenge.resources.UserResource;
import com.backend.challenge.service.LoginService;
import com.backend.challenge.service.exception.UserAlreadyExistsException;
import com.backend.challenge.utils.JSONUtil;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import spark.Request;
import spark.Response;
import spark.Route;

public class UsersController {

    private static LoginService loginService;

    public static Route createUser = (Request req, Response resp) -> {
        UserResource user = new Gson().fromJson(req.body(), UserResource.class);
        resp.type("application/json");

        if (user==null || user.getUsername() == null || user.getPassword() == null) {
            return ErrorResource.getError(resp, 400, "Username / password is required");
        }

        loadService();
        try {
            return JSONUtil.dataToJson(LoginResponseResource.builder().id(loginService.createUser(user.getUsername(), user.getPassword()).getId()).build());

        } catch (UserAlreadyExistsException ue) {
            return ErrorResource.getError(resp, 409, "Username already exists");
        } catch (Exception e) {
            resp.status(500);
            return null;
        }
    };

    private static void loadService() { //static method implementation above hence needed 
        if (loginService == null) { Injector injector = Guice.createInjector(new BasicModule());
            loginService = injector.getInstance(LoginService.class);
        }
    }
}
