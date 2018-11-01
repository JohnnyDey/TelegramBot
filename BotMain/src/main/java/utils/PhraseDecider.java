package utils;

import command.AbstractCommand;
import command.Command;
import command.factory.CommandFactory;
import jpa.entity.User;
import jpa.entity.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public abstract class PhraseDecider {

    private final Logger logger = LoggerFactory.getLogger(PhraseDecider.class);

    @Inject
    private CommandFactory factory;

    @Inject
    @Named(value = "execCommand")
    private Instance<Command> commandInstance;

    protected List<Object> onText(String message, User user){
        logger.debug("User " + user + "send message: " + message);
        setPropertiesForCommand(message, user);

        Command command = commandInstance.get();
        if(command.getStatus().equals(AbstractCommand.Status.NEW)){
            return command.execute(message.trim(), user);
        }else {
            if(message.startsWith(CommandFactory.CANCEL)){
                command.interrupt();
                return commandInstance.get().execute(message, user);
            } else {
                command.clearPhases();
                return command.nextPhase(message.trim(), user);
            }
        }
    }

    private void setPropertiesForCommand(String message, User user){
        factory.setUser(new UserId(user.getAppId(), User.AppType.valueOf(user.getAppType())));
        factory.setMessage(message);
    }

}
