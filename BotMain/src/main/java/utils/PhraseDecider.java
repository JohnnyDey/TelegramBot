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
import static startegy.RemindCommand.ID_ARG;
import static startegy.StartCommand.NAME_ARG;

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

    protected List<Object> onText(String message, User user){
        logger.debug("User " + user + "send message: " + message);
        Command command;
        if(message.startsWith("/timers")){
            command = new TimersCommand();
        } else if (message.startsWith("/spam")){
            command = new NotificationCommand();
        }else if("/start".equals(message)){
            command = new StartCommand();
            command.putArgs(NAME_ARG, user.getUserName());
        } else if(message.startsWith("/remind")){
            command = new RemindCommand();
            command.putArgs(ID_ARG, user.getAppId());
        } else if("/help".equals(message)) {
            command = new HelpCommand();
        } else if(message.startsWith("/register")){
            command = new RegisterCommand();
        } else if("/info".equals(message)) {
            command = new InfoCommand();
        } else if(message.startsWith("/notifyEveryOne")){
            command = new NotifyAllCommand();
            command.putArgs(EVENT, notifyAllEvent);
        }else  {
            command = new GeneralCommand();
        }

        //костыли убрать
        command.setPhraseUtil(phraseUtil);
        command.setTimersService(timersService);
        command.setUserServiceImp(userServiceImp);
        return command.execute(message.replace("/notifyEveryOne", "").trim(), user);
    }

}
