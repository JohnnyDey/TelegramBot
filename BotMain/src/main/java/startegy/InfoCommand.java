package startegy;

import jpa.entity.User;

import java.util.List;

public class InfoCommand extends CommonCommand implements Command {

    public static final String ID_ARG = "id";

    @Override
    public List<Object> execute(String message, User user) {
        phrases.addAll(phraseUtil.getInfoPhrase(user));
        stop();
        return phrases;
    }
}
