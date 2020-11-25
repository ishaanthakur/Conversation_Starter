package com.backend.challenge.service.impl;

import com.backend.challenge.repository.UserRepository;
import com.backend.challenge.repository.model.Token;
import com.backend.challenge.repository.model.User;
import com.backend.challenge.resources.LoginResponseResource;
import com.backend.challenge.service.LoginService;
import com.backend.challenge.service.exception.AuthException;
import com.backend.challenge.service.exception.UserAlreadyExistsException;
import com.backend.challenge.service.exception.UserNotFoundException;
import com.backend.challenge.service.util.EncryptionService;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginServiceImpl implements LoginService {

    @Inject
    private AuthServiceImpl auth;
    @Inject
    private UserRepository repository;

    @Override
    public LoginResponseResource createUser(String username, String password) {
        
        if (repository.existsByUsername(username)) { //if repo exists validation
            log.info("User {} already exists", username);
            throw new UserAlreadyExistsException();
        }

        // Creating user
        String encryptedPassword = EncryptionService.encrypt(password).orElseThrow(AuthException::new);
        User newUser = User.builder().username(username).password(encryptedPassword).build();
        repository.save(newUser);
        Token token = auth.createToken(newUser); // Token creting
        return LoginResponseResource.builder().id(token.getUser().getId()).build();
    }

    @Override
    public LoginResponseResource loginUser(String username, String password) {

        String encryptedPassword = EncryptionService.encrypt(password).orElseThrow(AuthException::new);
        User foundUser = repository.findByUsernameAndAndPassword(username, encryptedPassword).orElseThrow(UserNotFoundException::new);
        Token foundToken = auth.getToken(foundUser);
        return LoginResponseResource.builder().id(foundToken.getUser().getId()).token(foundToken.getToken()).build();
    }

    @Override
    public Integer getUserId(String token) {
        return auth.getToken(token).getUser().getId();
    }
}
