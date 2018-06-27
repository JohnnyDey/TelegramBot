package jpa.service;


import jpa.entity.User;

public interface UserService {

    User getUser(Long id);

    void saveUser(Long id, String name, String last, String userName);

    void saveUser(User user);
}
