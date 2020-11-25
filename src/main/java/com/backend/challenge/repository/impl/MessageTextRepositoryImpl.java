package com.backend.challenge.repository.impl;

import java.util.*;
import java.util.stream.*;

import com.backend.challenge.repository.MessageContentRepository;
import com.backend.challenge.repository.config.GenericDao;
import com.backend.challenge.repository.model.Message;
import com.backend.challenge.repository.model.MessageText;

import com.backend.challenge.resources.MessageContentResource;
import com.backend.challenge.resources.MessageContentResourceType;
import com.backend.challenge.resources.MessageResource;

import lombok.extern.slf4j.Slf4j;




@Slf4j
public class MessageTextRepositoryImpl extends GenericDao<MessageText, Integer> implements MessageContentRepository {

    @Override
    public Boolean support(MessageContentResourceType type) {
        return MessageContentResourceType.TEXT.equals(type);
    }

    @Override
    public void save(Message message, MessageContentResource messageContent) {
        MessageText messageText = fromMessageResource(message, messageContent);
        super.save(messageText);
    }

    @Override
    public List<MessageResource> listMessages(List<Integer> ids) {
        if (ids.isEmpty()) return new ArrayList<>();
        return listFromMessagesIds(ids, MessageText.class).map(value -> toMessageResource(value)).collect(Collectors.toList());
    }

    private MessageText fromMessageResource(Message message, MessageContentResource messageContent) {
        return MessageText.builder().message(message).text(messageContent.getText()).build();
    }

    private MessageResource toMessageResource(MessageText value) {
        MessageContentResource content =  MessageContentResource.builder().text(value.getText()).build();
        return toMessageResource(value.getMessage(), content);
    }
}
