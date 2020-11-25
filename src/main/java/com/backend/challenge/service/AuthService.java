package com.backend.challenge.service;

import com.backend.challenge.repository.model.Token;
import com.backend.challenge.repository.model.User;

public interface AuthService {

    Token createToken(User user);

    Token getToken(User user);

    Token getToken(String token);

    Boolean isValid(String token);
}
