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

    @Inject
    private Instance<Command> commandInstance;

    private String message;

    private UserId user;

    private Map<UserId, Command> sessions = new ConcurrentHashMap<>();

    @Produces
    @Named(value = "execCommand")
    public Command getCommand(){
        Command command = sessions.computeIfAbsent(user, s -> getNewCommand());
        if(command.getStatus().equals(CommonCommand.Status.STOPPED)){
            command = getNewCommand();
            sessions.put(user, command);
        }
        return command;
    }

    private Command getNewCommand(){
        if(message.startsWith("/timers")){
            return commandInstance.select(TimersCommand.class).get();
        } else if (message.equalsIgnoreCase("/spam")){
            return commandInstance.select(NotificationCommand.class).get();
        } else if(message.equalsIgnoreCase("/remind")){
            return commandInstance.select(RemindCommand.class).get();
        } else if(message.equalsIgnoreCase("/help")) {
            return commandInstance.select(HelpCommand.class).get();
        } else if(message.equalsIgnoreCase("/register")){
            return commandInstance.select(RegisterCommand.class).get();
        } else if(message.equalsIgnoreCase("/info")) {
            return commandInstance.select(InfoCommand.class).get();
        } else if(message.equalsIgnoreCase("/notifyEveryOne")){
            return commandInstance.select(NotifyAllCommand.class).get();
        } else if(message.equalsIgnoreCase("/timezone")){
            return commandInstance.select(TimeZoneCommand.class).get();
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
