package com.vkbot.api.keyboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Button {

    @SerializedName("action")
    @Expose
    private Action action;
    @SerializedName("color")
    @Expose
    private String color;

    public Action getAction() {
        return action;
    }

    void setAction(Action action) {
        this.action = action;
    }

    public Color getColor() {
        return Color.valueOf(color);
    }

    void setColor(Color color) {
        this.color = color.name().toLowerCase();
    }

    public enum Color{
        PRIMARY,
        DEFAULT,
        NEGATIVE,
        POSITIVE
    }
}
