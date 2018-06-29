package startegy;

import jpa.entity.User;

import java.util.List;

public class HelpCommand extends CommonCommand implements Command{
    @Override
    public List<String> execute(String message, User user) {
        phrases.add(phraseUtil.getHelp());
        return phrases;
    }
}
