package com.vkbot.vk.api.keyboard;

import utils.KeyboardMap;

public class KeyboardMatcher {

    public static Keyboard match (KeyboardMap keyboardMap){
        switch (keyboardMap){
            case LIST:
                return createListMap();
            case CANCEL:
                return createCancelMap();
            case SET_DAY:
                return createSetDayMap();
            case SET_TIME:
                return createSetTimeMap();
            case YES_OR_NO:
                return createYesNoMap();
        }
        return null;
    }

    private static Keyboard createSetTimeMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.DEFAULT, "ВРЕМЕННО НЕДОСТУПНО")
                .addToLine(0, Button.Color.NEGATIVE, "/хватит");
    }

    private static Keyboard createSetDayMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.DEFAULT, "ВРЕМЕННО НЕДОСТУПНО")
                .addToLine(0, Button.Color.NEGATIVE, "/хватит");
    }

    private static Keyboard createYesNoMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.POSITIVE, "Да")
                .addToLine(0, Button.Color.NEGATIVE, "Нет");
    }

    private static Keyboard createCancelMap() {
        return generalKeyboard().addToLine(0, Button.Color.NEGATIVE, "/хватит");
    }

    private static Keyboard createListMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.PRIMARY, "/помощь")
                .addToLine(0, Button.Color.PRIMARY, "/инфо")
                .addToLine(1, Button.Color.PRIMARY, "/напомни")
                .addToLine(1, Button.Color.PRIMARY, "/таймзона")
                .addToLine(1, Button.Color.PRIMARY, "/спам");
    }

    private static Keyboard generalKeyboard(){
        Keyboard keyboard = new Keyboard(false);
        keyboard.setOneTime(true);
        return keyboard;
    }
}
