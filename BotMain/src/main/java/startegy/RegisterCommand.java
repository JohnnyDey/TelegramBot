package startegy;

import jpa.entity.User;

import java.util.List;

public class RegisterCommand extends CommonCommand implements Command{


    @Override
    public List<Object> execute(String message, User user) {
        String name = message.replace("/register", "").trim();
        if(name.length() == 0 ){
            phrases.addAll(phraseUtil.emptyName());
        }else {
            user.setUserName(name);
            userServiceImp.saveUser(user);
            phrases.addAll(phraseUtil.registered(user.getUserName()));
        }
        return phrases;
    }

    public void altExecute(User user) {
        userServiceImp.saveUser(user);
    }
}
