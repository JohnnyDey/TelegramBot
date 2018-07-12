package command;

import jpa.entity.User;

import java.util.List;


public interface Command {
    List<Object> execute(String message, User user);

    void interrupt();

    CommonCommand.Status getStatus();

    List<Object> nextPhase(String message, User user);

    void clearPhases();
}
