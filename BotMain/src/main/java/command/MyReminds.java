package command;

import jpa.entity.User;

import javax.ejb.Timer;
import java.util.List;

public class MyReminds extends AbstractCommand {

    private List<Timer> timers;

    @Override
    public List<Object> execute(String message, User user) {
        return refreshTimers(user.getAppId());
    }

    private List<Object> refreshTimers(Long id){
        timers = timersService.findTimers(id);
        if (timers.size() == 0){
            putPhases(phraseUtil.noTimers());
            return finishExecution();
        }else {
            putPhases(phraseUtil.timersList(timers));
            return completeExecution();
        }
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        Integer index = Integer.parseInt(message) - 1;
        if(timers.size() > index && index >= 0){
            timers.get(index).cancel();
            return refreshTimers(user.getAppId());
        } else {
            putPhases(phraseUtil.toBigInteger());
        }
        return completeExecution();
    }


}
