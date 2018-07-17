package com.tgbot.utils;

import org.telegram.telegrambots.api.objects.Message;
import utils.PhraseDecider;

import java.util.List;

public class TgDeciderImpl extends PhraseDecider implements TgDecider{

    public List<Object> onText(Message message){
//        User user = userServiceImp.getUserByAppId(message.getChatId(), User.AppType.TG.name());
//        if(user == null){
//            user = new User();
//            user.setId(message.getChatId());
//            user.setUserName(message.getChat().getUserName());
//            user.setNotify(true);
//            user.setAppType(User.AppType.TG.name());
//            userServiceImp.saveUser(user);
//        }
        return null; //onText(message.getText().trim(), user);
    }

}
