package startegy;

import jpa.entity.User;

import java.util.List;

public class InfoCommand extends CommonCommand implements Command {

    public static final String ID_ARG = "id";

    @Override
    public List<String> execute(String message, User user) {
        phrases.add(phraseUtil.getInfoPhrase(user));
        return phrases;
    }
}
