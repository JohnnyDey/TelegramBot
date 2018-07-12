package command;

import action.NotifyAll;
import jpa.entity.User;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

public class NotifyAllCommand extends CommonCommand implements Command {

    @Inject
    private Event<NotifyAll> notifyAllEvent;

    @Override
    public List<Object> execute(String message, User user) {
        message = message.replace("/notifyEveryOne", "").trim();
        NotifyAll notifyAll = new NotifyAll();
        notifyAll.setUsersToNotify(userServiceImp.getAllUsersToNotify());
        notifyAll.setMsg(message);
        notifyAll.setDisclaimer(phraseUtil.howToNotify());
        notifyAllEvent.fire(notifyAll);
        return finishExecution();
    }
}
