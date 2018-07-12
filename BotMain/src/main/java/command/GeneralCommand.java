package command;

import jpa.entity.User;

import java.util.List;

public class GeneralCommand extends CommonCommand implements Command{

    @Override
    public List<Object> execute(String message, User user) {
        return finishExecution();
    }

}
