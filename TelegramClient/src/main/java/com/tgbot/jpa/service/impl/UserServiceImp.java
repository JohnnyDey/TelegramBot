package com.tgbot.jpa.service.impl;

import com.tgbot.jpa.entity.User;
import com.tgbot.jpa.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserServiceImp implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(Long id){
        return entityManager.createQuery("from "+ User.class.getName() + " user where user.telegramId = :id", User.class)
            .setParameter("id", id)
            .getSingleResult();
    }

    public void saveUser(Long id, String name, String last, String userName){
        User user = new User();
        user.setTelegramId(id);
        user.setFirstName(name);
        user.setLastName(last);
        user.setUserName(userName);
        saveUser(user);
    }

    public void saveUser(User user){
        if (!entityManager.contains(user)) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }
}
