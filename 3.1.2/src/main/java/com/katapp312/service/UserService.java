package com.katapp312.service;

import com.katapp312.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    User show(long id);

    List<User> getList();

    void update(long id, User user);

    void delete(long id);
}
