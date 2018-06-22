package jpa.service.impl;

import jpa.entity.User;
import jpa.repository.UserRepository;
import jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    private UserRepository repository;

    public User getUser(Long id){
        return repository.findOne(id);
    }

    public void saveUser(Long id, String name, String last){
        User user = new User();
        user.setTelegramId(id);
        user.setFirstName(name);
        user.setLastName(last);
    }

    public void saveUser(User user){
        repository.save(user);
    }
}
