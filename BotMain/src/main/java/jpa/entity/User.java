package jpa.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private Long telegramId;

    @Column
    private Long vkId;

    @Column
    private String tgName;

    @Column
    private String vkName;

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getTgName() {
        return tgName;
    }

    public void setTgName(String firstName) {
        this.tgName = firstName;
    }

    public String getVkName() {
        return vkName;
    }

    public void setVkName(String lastName) {
        this.vkName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVkId() {
        return vkId;
    }

    public void setVkId(Long vkId) {
        this.vkId = vkId;
    }
}
