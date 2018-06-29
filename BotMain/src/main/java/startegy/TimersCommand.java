package startegy;

import jpa.entity.User;

import java.util.List;

public class TimersCommand extends CommonCommand implements Command{
    @Override
    public List<String> execute(String message, User user) {
        timersService.getTimers().forEach(timer ->
                phrases.add(timer.getInfo().toString() + " " + timer.getNextTimeout())
        );
        return phrases;
    }
}
