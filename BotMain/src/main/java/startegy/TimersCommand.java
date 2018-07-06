package startegy;

import jpa.entity.User;

import java.util.List;

public class TimersCommand extends CommonCommand implements Command{
    @Override
    public List<Object> execute(String message, User user) {
        if(message.endsWith("Get")){
            timersService.getTimers().forEach(timer ->
                    phrases.add(timer.getInfo().toString() + " " + timer.getNextTimeout())
            );
            if(phrases.size() == 0){
                phrases.add("No timers");
            }
        } else if(message.endsWith("StopAll")){
            timersService.stopTimers();
            phrases.add("Stopped All timers");
        }
        return phrases;
    }
}
