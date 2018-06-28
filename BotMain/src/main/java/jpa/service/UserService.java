package jpa.service;


import jpa.entity.User;

public interface UserService {

    User getUser(Long id);

    User getUserByVkId(Long id);

    User getUserByTelegramId(Long id);

    User getUserByTelegramOrVkId(Long tgId, Long vkId);

    void saveUser(Long telegramId, Long vkId, String name, String last, String userName);

    void saveUser(User user);
}
