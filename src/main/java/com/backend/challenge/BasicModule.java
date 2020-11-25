package com.backend.challenge;

import java.util.*;

import com.backend.challenge.repository.MessageContentRepository;
import com.backend.challenge.repository.MessageRepository;
import com.backend.challenge.repository.TokenRepository;
import com.backend.challenge.repository.UserRepository;
import com.backend.challenge.repository.impl.*;

import com.backend.challenge.service.AuthService;
import com.backend.challenge.service.LoginService;
import com.backend.challenge.service.MessageService;
import com.backend.challenge.service.impl.AuthServiceImpl;
import com.backend.challenge.service.impl.LoginServiceImpl;
import com.backend.challenge.service.impl.MessageServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        /**
        * For Service
        */
        bind(LoginService.class).to(LoginServiceImpl.class).in(Scopes.SINGLETON);
        bind(AuthService.class).to(AuthServiceImpl.class).in(Scopes.SINGLETON);
        bind(MessageService.class).to(MessageServiceImpl.class).in(Scopes.SINGLETON);

        /**
        * For repository
        */
        bind(TokenRepository.class).to(TokenRepositoryImpl.class).in(Scopes.SINGLETON);
        bind(UserRepository.class).to(UserRepositoryImpl.class).in(Scopes.SINGLETON);
        bind(MessageRepository.class).to(MessageRepositoryImpl.class).in(Scopes.SINGLETON);

        Multibinder<MessageContentRepository> MB = Multibinder.newSetBinder(binder(), MessageContentRepository.class);
        MB.addBinding().to(MessageImageRepositoryImpl.class);
        MB.addBinding().to(MessageTextRepositoryImpl.class);
        MB.addBinding().to(MessageVideoRepositoryImpl.class);
    }
}
