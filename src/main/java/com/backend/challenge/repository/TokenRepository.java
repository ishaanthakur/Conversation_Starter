package com.backend.challenge.repository;

import java.util.*;

import com.backend.challenge.repository.model.Token;
import com.backend.challenge.repository.model.User;


//Testing left

public interface TokenRepository {
    Optional<Token> findByUser(User user);
    String save(Token token);
    Boolean existsById(String token);
    Optional<Token> findById(String token);
}
