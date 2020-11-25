package com.backend.challenge.controller;

import java.util.*;


import com.backend.challenge.BasicModule;
import com.backend.challenge.resources.ErrorResource;
import com.backend.challenge.resources.MessageResource;
import com.backend.challenge.service.LoginService;
import com.backend.challenge.service.MessageService;
import com.backend.challenge.service.exception.UserNotFoundException;
import com.backend.challenge.utils.JSONUtil;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;


public class MessagesController {
    private static LoginService loginService;
    private static MessageService messageService;

    public static Route sendMessage = (Request req, Response rep) -> {

        // Sending the message
        MessageResource message = new Gson().fromJson(req.body(), MessageResource.class);
        rep.type("application/json");

        // Validate payload for the message
        if (message==null || message.getSender() == null || message.getRecipient() == null || message.getContent() == null || message.getContent().getType() == null) return ErrorResource.getError(rep, 400, "Sender, Recipient and Type is required");

        // Validate payload for the content 
        if (!validateContent(message)) return ErrorResource.getError(rep, 400, "Content is invalid");
        
        loadService();
        //User should log for sending messagee
        
        String auth = req.headers("Authorization");
        Integer userLoggedId = null;


        if (auth != null) {
            // Removing "bearer" prefix

            String[] in = auth.split(" ");
            try {
                userLoggedId = loginService.getUserId(in[in.length - 1]);

            } catch (UserNotFoundException e) {
                return ErrorResource.getError(rep, 404, "Invalid token");
            }
        }

        if (userLoggedId == null || userLoggedId != message.getSender()) return ErrorResource.getError(rep, 403, "Cannot send message for other person");
        

        try {
            MessageResource response = messageService.save(message);
            return JSONUtil.dataToJson(MessageResource.builder().id(response.getId()).timestamp(response.getTimestamp()).build());

        } catch (UserNotFoundException e) {
            return ErrorResource.getError(rep, 404, "The other user to whom message is sent is not found");
        }
    };

    private static Boolean validateContent(MessageResource message) {
        Boolean validContent = true;
        switch (message.getContent().getType()) {
            case TEXT: validContent = message.getContent().getText() != null;
            break;
            case IMAGE: validContent = message.getContent().getHeight() != null && message.getContent().getWidth() != null && message.getContent().getUrl() != null;
            break;
            case VIDEO: validContent = message.getContent().getUrl() != null && message.getContent().getSource() != null;
        }
        return validContent;
    }

    public static Route getMessages = (Request req, Response rep) -> {
        rep.type("application/json");
        
        //Retrieve messages
        Integer recipient = req.queryMap().get("recipient").integerValue();
        Integer start = req.queryMap().get("start").integerValue();
        Integer limit = req.queryMap().get("limit").integerValue();
        
        if(limit == null) limit = 100;
        
        // Validate payload
        if (start == null || recipient == null) return ErrorResource.getError(rep, 400, "Recipient and start query attributes required");
        

        loadService();
        // User must be logged to read a message
        String auth = req.headers("Authorization");
        Integer userLoggedId = null;
        if (auth != null) {
            // Removing "bearer" prefix

            String[] in = auth.split(" ");

            try {
                userLoggedId = loginService.getUserId(in[in.length - 1]);
            } catch (UserNotFoundException nf) {
                return ErrorResource.getError(rep, 404, "No user found because token invalid");
            }
        }

        try {
            List<MessageResource> response = messageService.getMessages(userLoggedId, recipient, start, limit);
            return JSONUtil.dataToJson(response);

        } catch (UserNotFoundException e) {
            return ErrorResource.getError(rep, 404, "Recipient is not found");
        }
    };


    private static void loadService() { //above is a static method hence need dependency injection 
        if (loginService == null || messageService == null) {
            Injector injector = Guice.createInjector(new BasicModule());
            if (loginService == null) loginService = injector.getInstance(LoginService.class); 
            if (messageService == null) messageService = injector.getInstance(MessageService.class); 
        }
    }
}
