package startegy;

import jpa.entity.User;

import java.util.List;

public class NotificationCommand extends CommonCommand implements Command {

    @Override
    public List<Object> execute(String message, User user) {
        if (user.isNotify()){
            phrases.add(phraseUtil.askToStopNotify());
        } else {
            phrases.add(phraseUtil.askToStartNotify());
        }
        return phrases;
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(message.toLowerCase().startsWith("нет")){
            phrases.addAll(phraseUtil.notifyNoChanges());
            stop();
        } else if(message.toLowerCase().startsWith("да")){
            if(user.isNotify()){
                phrases = phraseUtil.notifyNo();
            } else {
                phrases = phraseUtil.notifyYes();
            }
            changeSub(user);
            stop();
        } else {
            if(user.isNotify()){
                phrases.add(phraseUtil.getNotifyHelp("выключить"));
            }else {
                phrases.add(phraseUtil.getNotifyHelp("включить"));
            }

        }
        return phrases;
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
