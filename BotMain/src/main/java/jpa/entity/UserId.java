package jpa.entity;

import java.util.Objects;

public class UserId {

    private Long appId;

    private User.AppType type;

    public UserId(Long appId, User.AppType type) {
        this.appId = appId;
        this.type = type;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public User.AppType getType() {
        return type;
    }

    public void setType(User.AppType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(appId, userId.appId) &&
                type == userId.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(appId, type);
    }
}
