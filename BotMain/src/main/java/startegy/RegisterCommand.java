package startegy;

import jpa.entity.User;

import java.util.List;

public class RegisterCommand extends CommonCommand implements Command{


    @Override
    public List<String> execute(String message, User user) {
        String name = message.replace("/register", "").trim();
        if(name.length() == 0 ){
            phrases.add(phraseUtil.emptyName());
        }else {
            user.setVkName(name);
            userServiceImp.saveUser(user);
            phrases.add(phraseUtil.registered(user.getVkName()));
        }
        return phrases;
    }

    public void altExecute(User user) {
        userServiceImp.saveUser(user);
    }
}
