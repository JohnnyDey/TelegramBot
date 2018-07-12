package command;

import jpa.entity.User;

import java.util.List;

public class NotificationCommand extends CommonCommand implements Command {

    @Override
    public List<Object> execute(String message, User user) {
        if (user.isNotify()){
            putPhase(phraseUtil.askToStopNotify());
        } else {
            putPhase(phraseUtil.askToStartNotify());
        }
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(message.toLowerCase().startsWith("нет")){
            putPhases(phraseUtil.notifyNoChanges());
            return finishExecution();
        } else if(message.toLowerCase().startsWith("да")){
            if(user.isNotify()){
                putPhases(phraseUtil.notifyNo());
            } else {
                putPhases(phraseUtil.notifyYes());
            }
            changeSub(user);
            return finishExecution();
        } else {
            if(user.isNotify()){
                putPhase(phraseUtil.getNotifyHelp("выключить"));
            }else {
                putPhase(phraseUtil.getNotifyHelp("включить"));
            }

        }
        return completeExecution();
    }

    private void changeSub(User user){
        if(user.isNotify()){
            user.setNotify(false);
        } else {
            user.setNotify(true);
        }
        userServiceImp.saveUser(user);
    }
}
