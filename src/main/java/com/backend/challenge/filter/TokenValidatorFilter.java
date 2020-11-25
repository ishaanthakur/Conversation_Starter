package com.backend.challenge.filter;

import com.backend.challenge.BasicModule;
import com.backend.challenge.service.AuthService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class TokenValidatorFilter {

    private static AuthService authService;

    public static Filter validateUser = (Request req, Response resp) -> {
        loadService();
        String auth = req.headers("Authorization");

        boolean isTokenValid = false;
        if (auth != null) {
      
            String[] in = auth.split(" ");
            isTokenValid = authService.isValid(in[in.length-1]);
        }

        if (!isTokenValid) {
            Spark.halt(401, "Not valid token");
        }
    };


    private static void loadService() {
        if (authService == null) {
            Injector injector = Guice.createInjector(new BasicModule());
            authService = injector.getInstance(AuthService.class);
        }
    }
}
