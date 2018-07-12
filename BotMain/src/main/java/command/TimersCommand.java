package command;

import jpa.entity.User;

import java.util.List;

public class TimersCommand extends CommonCommand implements Command{
    @Override
    public List<Object> execute(String message, User user) {
        if(message.endsWith("Get")){
            timersService.getTimers().forEach(timer ->
                    putPhase(timer.getInfo().toString() + " " + timer.getNextTimeout())
            );
            if(noPhrases()){
                putPhase("No timers");
            }
        } else if(message.endsWith("StopAll")){
            timersService.stopTimers();
            putPhase("Stopped All timers");
        }
        return finishExecution();
    }
}
