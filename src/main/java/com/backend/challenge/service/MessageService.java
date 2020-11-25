package com.backend.challenge.service;

import com.backend.challenge.resources.MessageResource;

import java.util.*;

public interface MessageService {

    MessageResource save(MessageResource message);

    List<MessageResource> getMessages(Integer userLoggedId, Integer recipient, Integer start, Integer limit);

}
