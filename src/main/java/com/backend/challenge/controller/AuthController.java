package com.backend.challenge.controller;

import java.util.*;

import com.backend.challenge.BasicModule;

import com.backend.challenge.resources.ErrorResource;
import com.backend.challenge.resources.UserResource;

import com.backend.challenge.service.LoginService;
import com.backend.challenge.service.exception.UserNotFoundException;

import com.backend.challenge.utils.JSONUtil;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.*;

public class AuthController {

    public static Route login = (Request req, Response resp) -> {

        //Login and returning a token

        UserResource user = new Gson().fromJson(req.body(), UserResource.class);
        
        resp.type("application/json");

        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return ErrorResource.getError(resp, 400, "Username, password  needed");
        }

        try {
            return JSONUtil.dataToJson(getService().loginUser(user.getUsername(), user.getPassword()));
        } catch (UserNotFoundException ue) {
            return ErrorResource.getError(resp, 404, "User / password wrong");
        } catch (Exception e) {
            resp.status(500);
            return null;
        }
    };

    private static LoginService getService() { //adding this service as the class is not static and need a way to inject the dependency
        Injector injector = Guice.createInjector(new BasicModule());
        return injector.getInstance(LoginService.class);
    }
}
