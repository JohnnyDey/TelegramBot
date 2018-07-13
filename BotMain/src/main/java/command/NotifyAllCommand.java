package command;

import action.NotifyAll;
import jpa.entity.User;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

public class NotifyAllCommand extends AbstractCommand {

    @Inject
    private Event<NotifyAll> notifyAllEvent;

    @Override
    public List<Object> execute(String message, User user) {
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        NotifyAll notifyAll = new NotifyAll();
        notifyAll.setUsersToNotify(userServiceImp.getAllUsersToNotify());
        notifyAll.setMsg(message);
        notifyAll.setDisclaimer(phraseUtil.howToNotify());
        notifyAllEvent.fire(notifyAll);
        return finishExecution();
    }
}
