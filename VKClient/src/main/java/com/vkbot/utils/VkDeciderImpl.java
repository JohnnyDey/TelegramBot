package com.vkbot.utils;

import com.petersamokhin.bots.sdk.objects.Message;
import jpa.entity.User;
import utils.PhraseDecider;

import java.util.List;

public class VkDeciderImpl extends PhraseDecider implements VkDecider{

    public List<Object> onText(Message message){
        User user = userServiceImp.getUserByAppId(Long.valueOf(message.authorId()), User.AppType.VK.name());
        if(user == null){
            user = new User();
            user.setAppId(Long.valueOf(message.authorId()));
            user.setNotify(true);
            user.setAppType(User.AppType.VK.name());
            userServiceImp.saveUser(user);
        }
        return onText(message.getText().trim(), user);
    }

}
