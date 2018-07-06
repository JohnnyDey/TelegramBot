package startegy;

import jpa.entity.User;

import java.util.List;

public class NotificationCommand extends CommonCommand implements Command {

    @Override
    public List<Object> execute(String message, User user) {

        User userByAppId = userServiceImp.getUserByAppId(user.getAppId(), user.getAppType());
        if(message.endsWith("no")){
            userByAppId.setNotify(false);
            phrases = phraseUtil.notifyNo();
        } else if(message.endsWith("yes")){
            userByAppId.setNotify(true);
            phrases = phraseUtil.notifyYes();
        } else {
            phrases.add(phraseUtil.getNotifyHelp());
            return phrases;
        }
        userServiceImp.saveUser(userByAppId);
        return phrases;
    }
}
