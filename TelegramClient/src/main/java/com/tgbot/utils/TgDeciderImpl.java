package com.tgbot.utils;

import jpa.entity.User;
import org.telegram.telegrambots.api.objects.Message;
import startegy.RegisterCommand;
import utils.PhraseDecider;

import java.util.List;

public class TgDeciderImpl extends PhraseDecider implements TgDecider{

    public List<String> onText(Message message){
        User userByTelegramId = userServiceImp.getUserByTelegramId(message.getChatId());
        if(userByTelegramId == null){
            userByTelegramId = new User();
            userByTelegramId.setTelegramId(message.getChatId());
            userByTelegramId.setTgName(message.getChat().getUserName());
            new RegisterCommand().altExecute(userByTelegramId);
        }
        return onText(message.getText().trim(), userByTelegramId);
    }

    @Override
    public User updateName(User user, String name) {
        return null;
    }

    @Override
    public Long getId(User user) {
        return user.getTelegramId();
    }

    @Override
    public String getName(User user) {
        return user.getTgName();
    }

    @Override
    public User getUserById(User user) {
        return userServiceImp.getUserByTelegramId(user.getTelegramId());
    }


}
