package startegy;

import jpa.entity.User;

import java.util.List;

public class StartCommand extends CommonCommand implements Command {

    public static final String NAME_ARG = "name";

    @Override
    public List<String> execute(String message, User user) {
        phrases.add(phraseUtil.getFirstGreetPhrase((String) args.get(NAME_ARG)));
        phrases.add(phraseUtil.getSecondGreetPhrase());
        return phrases;
    }
}
