package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> showAllUsers();

    User findByLoginUser(String login);

    User show(Long id);

    void add(User user);

    void delete(Long id);

    void update(User user);

}
