package startegy;

import jpa.entity.User;

import java.util.List;

public class RegisterCommand extends CommonCommand implements Command{


    @Override
    public List<Object> execute(String message, User user) {
        phrases.add(phraseUtil.howToName(user.getUserName()));
        return phrases;
    }

    public void altExecute(User user) {
        userServiceImp.saveUser(user);
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(message.length() == 0 ){
            phrases.addAll(phraseUtil.emptyName());
        }else {
            user.setUserName(message);
            userServiceImp.saveUser(user);
            phrases.addAll(phraseUtil.registered(user.getUserName()));
            stop();
        }
        return phrases;
    }
}
