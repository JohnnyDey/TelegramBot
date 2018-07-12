package command;

import jpa.entity.User;

import java.util.List;

public class InfoCommand extends CommonCommand implements Command {


    @Override
    public List<Object> execute(String message, User user) {
        putPhases(phraseUtil.getInfoPhrase(user));
        return finishExecution();
    }
}
