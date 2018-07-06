package action;

import jpa.entity.User;

import java.util.List;

public class NotifyAll {

    private String msg;
    private List<User> usersToNotify;

    public List<User> getUsersToNotify() {
        return usersToNotify;
    }

    public void setUsersToNotify(List<User> usersToNotify) {
        this.usersToNotify = usersToNotify;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
