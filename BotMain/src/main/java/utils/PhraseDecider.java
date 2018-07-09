package utils;

import action.NotifyAll;
import jpa.entity.User;
import jpa.service.TimersService;
import jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import startegy.*;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

import static startegy.NotifyAllCommand.EVENT;

public abstract class PhraseDecider {

    private Logger logger = LoggerFactory.getLogger(PhraseDecider.class);

    @Inject
    protected UserService userServiceImp;

    @Inject
    private TimersService timersService;

    @Inject
    private PhraseUtil phraseUtil;

    @Inject
    private Event<NotifyAll> notifyAllEvent;

    private Command command;

    protected List<Object> onText(String message, User user){
        logger.debug("User " + user + "send message: " + message);

        if(command == null || command.isStopped()){
            if(message.startsWith("/timers")){
                command = new TimersCommand();
            } else if (message.startsWith("/spam")){
                command = new NotificationCommand();
            } else if(message.startsWith("/remind")){
                command = new RemindCommand();
            } else if(message.startsWith("/help")) {
                command = new HelpCommand();
            } else if(message.startsWith("/register")){
                command = new RegisterCommand();
            } else if(message.startsWith("/info")) {
                command = new InfoCommand();
            } else if(message.startsWith("/notifyEveryOne")){
                command = new NotifyAllCommand();
                command.putArgs(EVENT, notifyAllEvent);
            } else if(message.startsWith("/timezone")){
                command = new TimeZoneCommand();
            } else {
                command = new HelpCommand();
            }

            command.setPhraseUtil(phraseUtil);
            command.setTimersService(timersService);
            command.setUserServiceImp(userServiceImp);
            return command.execute(message.trim(), user);
        }else {
            if(message.startsWith("/cancel")){
                command.stop();
                return null;
            } else {
                command.clearPhases();
                return command.nextPhase(message, user);
            }
        }
    }

}
