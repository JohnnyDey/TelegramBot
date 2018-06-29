package jpa.service.impl;

import jpa.entity.User;
import jpa.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public class UserServiceImp implements UserService {

    @PersistenceContext(unitName = "bot.persistence.tb")
    private EntityManager entityManager;

    public User getUser(Long id){
        return entityManager.find(User.class, id);
    }

    public User getUserByVkId(Long id) {
        try {
            return entityManager.createQuery("from "+ User.class.getName() + " user where user.vkId = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public User getUserByTelegramId(Long id) {
        try {
            return entityManager.createQuery("from "+ User.class.getName() + " user where user.telegramId = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public User getUserByTelegramOrVkId(Long tgId, Long vkId) {
        try {
            return entityManager.createQuery("from "+ User.class.getName() + " user where user.telegramId = :tg or user.vkId = :vk", User.class)
                    .setParameter("tg", tgId)
                    .setParameter("vk", vkId)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public void saveUser(Long telegramId, Long vkId, String name, String last, String userName) {
        User user = new User();
        user.setTelegramId(telegramId);
        user.setVkId(vkId);
        user.setTgName(name);
        user.setVkName(last);
        saveUser(user);
    }

    //ToDO: решить пробелму с мерджем из разных ботов
    @Transactional(Transactional.TxType.REQUIRED)
    public void saveUser(User user){
        User storedUser = getUserByTelegramOrVkId(user.getTelegramId(), user.getVkId());
        if (storedUser != null) {
            user.setId(storedUser.getId());
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }
}
