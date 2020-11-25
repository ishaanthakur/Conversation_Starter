package com.backend.challenge.repository;

import java.util.*;

import com.backend.challenge.repository.model.User;

//Testing left

public interface UserRepository {
    Optional<User> findByUsernameAndAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    Integer save(User user);
    User get(Integer id);
}
