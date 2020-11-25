package com.backend.challenge.repository;

import java.util.*;

import com.backend.challenge.repository.model.Message;
import com.backend.challenge.repository.model.User;

//Testing left

public interface MessageRepository {
    Integer save(Message message);
    List<Message> listMessages(User sender, User recipient, Integer start, Integer limit);
}
