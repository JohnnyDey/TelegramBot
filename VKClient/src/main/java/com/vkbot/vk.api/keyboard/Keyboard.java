
package com.vkbot.vk.api.keyboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    Keyboard(Boolean oneTime) {
        this.oneTime = oneTime;
    }

    Keyboard addToLine(int index, Button.Color color, String text){
        if (buttons.size() < index) return this;
        if (buttons.size() == index) buttons.add(new ArrayList<>());

        Button button = new Button();
        buttons.get(index).add(button);
        button.setColor(color);
        Action action = new Action();
        action.setLabel(text);
        button.setAction(action);

        return this;
    }

    @SerializedName("one_time")
    @Expose
    private Boolean oneTime;
    @SerializedName("buttons")
    @Expose
    private List<List<Button>> buttons = new ArrayList<>();

    public Boolean getOneTime() {
        return oneTime;
    }

    void setOneTime(Boolean oneTime) {
        this.oneTime = oneTime;
    }

    public List<List<Button>> getButtons() {
        return buttons;
    }

    public void setButtons(List<List<Button>> buttons) {
        this.buttons = buttons;
    }

}
