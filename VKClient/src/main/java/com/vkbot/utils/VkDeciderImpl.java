package com.vkbot.utils;

import com.petersamokhin.bots.sdk.objects.Message;
import jpa.entity.User;
import utils.PhraseDecider;

import javax.ejb.Stateless;
import java.util.List;

public class VkDeciderImpl extends PhraseDecider implements VkDecider{

    public List<String> onText(Message message){
        User userByVkId = userServiceImp.getUserByVkId(Long.valueOf(message.authorId()));
        if(userByVkId == null){
            userByVkId = new User();
            userByVkId.setVkId(Long.valueOf(message.authorId()));
            register(userByVkId);
        }
        return onText(message.getText(), userByVkId);
    }


    @Override
    public void updateName(User user, String name) {
        userServiceImp.getUserByVkId(user.getVkId());
        user.setVkName(name);
        register(user);
    }

    @Override
    public Long getId(User user) {
        return user.getVkId();
    }

    @Override
    public String getName(User user) {
        return user.getVkName();
    }

    @Override
    public User getUserById(User user) {
        return userServiceImp.getUserByVkId(user.getVkId());
    }
}
