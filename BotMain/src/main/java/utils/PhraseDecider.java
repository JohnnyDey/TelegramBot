package utils;

import jpa.entity.User;
import jpa.service.TimersService;
import jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import startegy.*;

import javax.inject.Inject;
import java.util.List;

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

    protected List<String> onText(String message, User user){
        logger.debug("User " + user + "send message: " + message);
        Command command;
        if("/timers".equals(message)){
            command = new TimersCommand();
        } else if("/start".equals(message)){
            command = new StartCommand();
            command.putArgs(NAME_ARG, getName(user));
        } else if(message.startsWith("/remind")){
            command = new RemindCommand();
            command.putArgs(ID_ARG, getId(user));
        } else if("/help".equals(message)) {
            command = new HelpCommand();
        } else if(message.startsWith("/register")){
            command = new RegisterCommand();
        } else if("/info".equals(message)) {
            if(user.getId() == null){
                user = getUserById(user);
            }
            command = new InfoCommand();
        } else {
            command = new GeneralCommand();
        }

        //костыли убрать
        command.setPhraseUtil(phraseUtil);
        command.setTimersService(timersService);
        command.setUserServiceImp(userServiceImp);
        return command.execute(message, user);
    }

    public abstract User updateName(User user, String name);

    public abstract Long getId(User user);

    public abstract String getName(User user);

    public abstract User getUserById(User user);
}
