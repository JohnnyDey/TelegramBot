package jpa.service.impl;

import jpa.entity.User;
import jpa.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public class UserServiceImp implements UserService {

    @PersistenceContext(unitName = "bot.persistence.tb")
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from " + User.class.getName(), User.class).getResultList();
    }

    @Override
    public List<User> getAllUsersToNotify() {
        return entityManager.createQuery("from " + User.class.getName() + " u where u.notify = true", User.class).getResultList();
    }

    public User getUserByAppId(Long id, String type){
        try {
            return entityManager.createQuery("from " + User.class.getName() + " u where u.appId = :id and u.appType = :type" , User.class)
                    .setParameter("id", id)
                    .setParameter("type", type)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }

    }

    public User getUser(Long id){
        return entityManager.find(User.class, id);
    }

    public void saveUser(Long id, String userName) {
        User user = new User();
        user.setAppId(id);
        user.setUserName(userName);
        saveUser(user);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveUser(User user){
        User storedUser = getUserByAppId(user.getAppId(), user.getAppType());
        if (storedUser != null) {
            user.setAppId(storedUser.getAppId());
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }
}
