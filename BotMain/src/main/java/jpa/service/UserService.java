package jpa.service;


import jpa.entity.User;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {

    List<User> getAllUsers();

    List<User> getAllUsersToNotify();

    User getUserByAppId(Long id, String type);

    User getUser(Long id);

    void saveUser(Long id, String name);

    void saveUser(User user);
}
