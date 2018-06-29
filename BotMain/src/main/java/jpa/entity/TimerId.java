package jpa.entity;

import java.io.Serializable;
import java.util.Objects;

public class TimerId implements Serializable {
    private Long id;
    private String msg;

    public TimerId(Long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerId timerId = (TimerId) o;
        return Objects.equals(id, timerId.id) &&
                Objects.equals(msg, timerId.msg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, msg);
    }

    @Override
    public String toString() {
        return "TimerId{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
