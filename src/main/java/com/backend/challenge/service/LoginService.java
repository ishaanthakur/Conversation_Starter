package com.backend.challenge.service;

import com.backend.challenge.resources.LoginResponseResource;

public interface LoginService {

    LoginResponseResource createUser(String username, String password);

    LoginResponseResource loginUser(String username, String password);

    Integer getUserId(String s);
}
