package startegy;

import jpa.entity.User;

import java.util.List;

public class HelpCommand extends CommonCommand implements Command{
    @Override
    public List<Object> execute(String message, User user) {
        phrases.addAll(phraseUtil.getHelp());
        stop();
        return phrases;
    }
}
