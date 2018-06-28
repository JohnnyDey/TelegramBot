package utils;

import jpa.entity.User;
import jpa.service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public abstract class PhraseDecider {

    @Inject
    UserService userServiceImp;

    List<String> onText(String message, User user){
        List<String> phrases = new ArrayList<String>();
        if("/start".equals(message)){
            register(user);

            phrases.add(RandomPhraseUtil.getFirstGreetPhrase(getName(user)));
            phrases.add(RandomPhraseUtil.getSecondGreetPhrase());
        } else if("/add".equals(message)) {
            register(user);
        } else if("/get".equals(message)) {
            User storedUser = userServiceImp.getUser(getId(user));
            phrases.add(RandomPhraseUtil.getInfoPhrase(storedUser));
        } else {
            phrases.add(RandomPhraseUtil.getRandomPhrase());
        }
        return phrases;
    }

    public abstract Long getId(User user);

    public abstract String getName(User user);

    void register(User user){
        userServiceImp.saveUser(user);
    }
}
