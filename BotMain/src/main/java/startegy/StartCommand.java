package startegy;

import jpa.entity.User;

import java.util.List;

public class StartCommand extends CommonCommand implements Command {

    public static final String NAME_ARG = "name";

    @Override
    public List<Object> execute(String message, User user) {
        phrases.addAll(phraseUtil.getGreetPhrases(args.get(NAME_ARG)));
        return phrases;
    }
}
