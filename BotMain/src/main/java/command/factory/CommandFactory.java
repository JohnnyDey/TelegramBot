package command.factory;

import command.*;
import jpa.entity.UserId;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class CommandFactory {

    public static final String INFO = "Информация";
    public static final String SPAM = "Спам";
    public static final String REMIND = "Напомнить";
    public static final String MY_REMINDS = "Мои напоминалки";
    public static final String CANCEL = "Хватит";
    public static final String NEXT = "Дальше";

    @Inject
    private Instance<Command> commandInstance;

    private String message;

    private UserId user;

    private final Map<UserId, Command> sessions = new ConcurrentHashMap<>();

    @Produces
    @Named(value = "execCommand")
    public Command getCommand(){
        Command command = sessions.computeIfAbsent(user, s -> getNewCommand());
        if(command.getStatus().equals(AbstractCommand.Status.STOPPED)){
            command = getNewCommand();
            sessions.put(user, command);
        }
        return command;
    }

    private Command getNewCommand(){
        if(message.startsWith("/timers")){
            return commandInstance.select(TimersCommand.class).get();
        } else if (message.equals(SPAM)){
            return commandInstance.select(NotificationCommand.class).get();
        } else if(message.equals(REMIND)){
            return commandInstance.select(RemindCommand.class).get();
        } else if(message.equals(INFO)) {
            return commandInstance.select(InfoCommand.class).get();
        } else if(message.startsWith("/сказатьВслух") && user.getAppId().equals(40092345L)){
            return commandInstance.select(NotifyAllCommand.class).get();
        } else if(message.equals(MY_REMINDS)){
            return commandInstance.select(MyReminds.class).get();
        } else {
            return commandInstance.select(HelpCommand.class).get();
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(UserId user) {
        this.user = user;
    }
}
