package com.backend.challenge.repository.impl;

import java.util.*;
import java.util.stream.*;

import com.backend.challenge.repository.MessageContentRepository;
import com.backend.challenge.repository.config.GenericDao;
import com.backend.challenge.repository.model.Message;
import com.backend.challenge.repository.model.MessageVideo;

import com.backend.challenge.resources.MessageContentResource;
import com.backend.challenge.resources.MessageContentResourceType;
import com.backend.challenge.resources.MessageResource;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class MessageVideoRepositoryImpl extends GenericDao<MessageVideo, Integer> implements MessageContentRepository {

    @Override
    public Boolean support(MessageContentResourceType type) {
        return MessageContentResourceType.VIDEO.equals(type);
    }

    @Override
    public void save(Message message, MessageContentResource messageContent) {
        MessageVideo messageVideo = fromMessageResource(message, messageContent);
        super.save(messageVideo);
    }

    @Override
    public List<MessageResource> listMessages(List<Integer> ids) {
        if (ids.isEmpty()) return new ArrayList<>();
        return listFromMessagesIds(ids, MessageVideo.class).map(value -> toMessageResource(value)).collect(Collectors.toList());
    }

    private MessageVideo fromMessageResource(Message message, MessageContentResource messageContent) {
        return MessageVideo.builder().message(message).source(messageContent.getSource()).url(messageContent.getUrl()).build();
    }

    private MessageResource toMessageResource(MessageVideo value) {
        MessageContentResource content =  MessageContentResource.builder().url(value.getUrl()).source(value.getSource()).build();
        return toMessageResource(value.getMessage(), content);
    }
}
