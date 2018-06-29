package startegy;

import jpa.entity.User;

import java.util.List;

public interface Command {
    List<String> execute(String message, User user);

    void putArgs(String key, Object val);
}
