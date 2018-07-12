package command;

import jpa.entity.User;

import java.util.List;

public class RegisterCommand extends AbstractCommand {

    @Override
    public List<Object> execute(String message, User user) {
        putPhase(phraseUtil.howToName(user.getUserName()));
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(message.length() == 0 ){
            putPhases(phraseUtil.emptyName());
            return completeExecution();
        }else {
            user.setUserName(message);
            userServiceImp.saveUser(user);
            putPhases(phraseUtil.registered(user.getUserName()));
            return finishExecution();
        }
    }
}
