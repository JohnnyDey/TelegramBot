package utils;

import com.petersamokhin.bots.sdk.objects.Message;
import jpa.entity.User;

import java.util.List;

public class VkDecider extends PhraseDecider {

    public List<String> onText(Message message){
        User userByVkId = userServiceImp.getUserByVkId(Long.valueOf(message.getMessageId()));
        if(userByVkId == null){
            userByVkId = new User();
            userByVkId.setVkId(Long.valueOf(message.getMessageId()));
            userByVkId.setVkName(null);             //TODO: найти имя
            register(userByVkId);
        }
        return onText(message.getText(), userByVkId);
    }


    public Long getId(User user) {
        return user.getVkId();
    }

    public String getName(User user) {
        return user.getVkName();
    }
}
