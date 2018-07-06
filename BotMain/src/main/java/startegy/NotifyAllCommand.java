package startegy;

import action.NotifyAll;
import jpa.entity.User;

import javax.enterprise.event.Event;
import java.util.Collections;
import java.util.List;

public class NotifyAllCommand extends CommonCommand implements Command {

    public static final String EVENT = "event";

    @Override
    public List<Object> execute(String message, User user) {
        NotifyAll notifyAll = new NotifyAll();
        notifyAll.setUsersToNotify(userServiceImp.getAllUsersToNotify());
        notifyAll.setMsg(message);
        ((Event<NotifyAll>) args.get(EVENT)).fire(notifyAll);
        return Collections.emptyList();
    }
}
