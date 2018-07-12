package command;

import jpa.entity.User;

import java.util.List;

public class InfoCommand extends AbstractCommand {


    @Override
    public List<Object> execute(String message, User user) {
        putPhases(phraseUtil.getInfoPhrase(user));
        return finishExecution();
    }
}
