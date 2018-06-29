package utils;

import jpa.entity.User;
import jpa.service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public abstract class PhraseDecider {

    @Inject
    protected UserService userServiceImp;

    protected List<String> onText(String message, User user){
        List<String> phrases = new ArrayList<String>();
        if("/start".equals(message)){
            phrases.add(RandomPhraseUtil.getFirstGreetPhrase(getName(user)));
            phrases.add(RandomPhraseUtil.getSecondGreetPhrase());
        } if(message.startsWith("/register")){
            updateName(user, message.replace("/register", "") );
            phrases.add(RandomPhraseUtil.registered(user.getVkName()));
        } else if("/info".equals(message)) {
            if(user.getId() == null){
                user = getUserById(user);
            }
            phrases.add(RandomPhraseUtil.getInfoPhrase(user));
        } else {
            phrases.add(RandomPhraseUtil.getRandomPhrase());
        }
        return phrases;
    }

    public abstract void updateName(User user, String name);

    public abstract Long getId(User user);

    public abstract String getName(User user);

    public abstract User getUserById(User user);

    protected void register(User user){
        userServiceImp.saveUser(user);

    }
}
