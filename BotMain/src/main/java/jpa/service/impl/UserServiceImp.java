package jpa.service.impl;

import jpa.entity.User;
import jpa.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public class UserServiceImp implements UserService {

    @PersistenceContext(unitName = "bot.persistence.tb")
    private EntityManager entityManager;

    public User getUser(Long id){
        return entityManager.find(User.class, id);
    }

    public User getUserByVkId(Long id) {
        return entityManager.createQuery("from "+ User.class.getName() + " user where user.vkId = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User getUserByTelegramId(Long id) {
        return entityManager.createQuery("from "+ User.class.getName() + " user where user.telegramId = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User getUserByTelegramOrVkId(Long tgId, Long vkId) {
        return entityManager.createQuery("from "+ User.class.getName() + " user where user.telegramId = :tg or user.vkId = :vk", User.class)
                .setParameter("tg", tgId)
                .setParameter("vk", vkId)
                .getSingleResult();
    }

    public void saveUser(Long telegramId, Long vkId, String name, String last, String userName) {
        User user = new User();
        user.setTelegramId(telegramId);
        user.setVkId(vkId);
        user.setTgName(name);
        user.setVkName(last);
        saveUser(user);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveUser(User user){
        if (!entityManager.contains(user)) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }
}
