package startegy;

import jpa.entity.User;
import jpa.service.TimersService;
import jpa.service.UserService;
import utils.PhraseUtil;

import java.util.List;

public interface Command {
    List<Object> execute(String message, User user);

    void putArgs(String key, Object val);

    void setUserServiceImp(UserService userServiceImp);

    void setTimersService(TimersService timersService);

    void setPhraseUtil(PhraseUtil phraseUtil);
}
