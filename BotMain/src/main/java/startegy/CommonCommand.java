package startegy;

import jpa.entity.User;
import jpa.service.TimersService;
import jpa.service.UserService;
import utils.PhraseUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonCommand {

    @Inject
    UserService userServiceImp;

    @Inject
    TimersService timersService;

    @Inject
    PhraseUtil phraseUtil;

    Map<String, Object> args = new HashMap<>();

    List<String> phrases = new ArrayList<>();

    public void putArgs(String key, Object val){
        args.put(key, val);
    }
}
