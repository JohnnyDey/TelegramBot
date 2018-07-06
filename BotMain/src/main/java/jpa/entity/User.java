package jpa.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private Long appId;

    @Column
    private String appType;

    @Column
    private String userName;

    @Column
    private boolean notify;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public enum AppType{
        VK,
        TG
    }
}
