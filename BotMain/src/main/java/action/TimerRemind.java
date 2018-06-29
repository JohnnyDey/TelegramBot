package action;

import jpa.entity.TimerId;

public class TimerRemind {
    public TimerRemind(TimerId id) {
        this.id = id;
    }

    private TimerId id;

    public TimerId getId() {
        return id;
    }

    public void setId(TimerId id) {
        this.id = id;
    }
}
