package utils;

import jpa.entity.User;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

public class TgDecider extends PhraseDecider {

    public List<String> onText(Message message){
        User userByTelegramId = userServiceImp.getUserByTelegramId(message.getChatId());
        if(userByTelegramId == null){
            userByTelegramId = new User();
            userByTelegramId.setTelegramId(message.getChatId());
            userByTelegramId.setTgName(message.getChat().getUserName());
            register(userByTelegramId);
        }
        return onText(message.getText(), userByTelegramId);
    }

    @Override
    public void updateName(User user, String name) {

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
