package command;

import jpa.entity.User;

import java.util.List;

public class HelpCommand extends CommonCommand implements Command{
    @Override
    public List<Object> execute(String message, User user) {
        putPhases(phraseUtil.getHelp());
        return finishExecution();
    }
}
