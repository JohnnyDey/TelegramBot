package jpa.service;


import jpa.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUser(Long id);

    void saveUser(Long id, String name, String last);

    void saveUser(User user);
}
