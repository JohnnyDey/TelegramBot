package command;

import jpa.entity.User;
import jpa.service.TimersService;
import jpa.service.UserService;
import utils.PhraseUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCommand implements Command {

    @Inject
    UserService userServiceImp;

    @Inject
    TimersService timersService;

    @Inject
    PhraseUtil phraseUtil;

    AbstractCommand() {}

    List<Object> completeExecution(){
        status = Status.MANAGED;
        return phrases;
    }

    List<Object> finishExecution(){
        status = Status.STOPPED;
        return phrases;
    }

    @Override
    public abstract List<Object> execute(String message, User user);

    public void interrupt(){
        status = Status.STOPPED;
    }

    private final List<Object> phrases = new ArrayList<>();

    void putPhase(Object o){
        phrases.add(o);
    }

    boolean noPhrases(){
        return phrases.size() == 0;
    }

    void putPhases(Collection o){
        phrases.addAll(o);
    }

    private Status status = Status.NEW;

    public Status getStatus() {
        return status;
    }

    public List<Object> nextPhase(String message, User user){
        return null;
    }

    public void clearPhases(){
        phrases.clear();
    }

    public enum Status{
        NEW,
        MANAGED,
        STOPPED
    }
}
