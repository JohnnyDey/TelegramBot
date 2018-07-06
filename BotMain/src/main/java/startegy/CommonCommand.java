package startegy;

import jpa.service.TimersService;
import jpa.service.UserService;
import utils.PhraseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonCommand {

    UserService userServiceImp;

    TimersService timersService;

    PhraseUtil phraseUtil;

    Map<String, Object> args = new HashMap<>();

    List<Object> phrases = new ArrayList<>();

    public void putArgs(String key, Object val){
        args.put(key, val);
    }

    public void setUserServiceImp(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    public void setTimersService(TimersService timersService) {
        this.timersService = timersService;
    }

    public void setPhraseUtil(PhraseUtil phraseUtil) {
        this.phraseUtil = phraseUtil;
    }
}
