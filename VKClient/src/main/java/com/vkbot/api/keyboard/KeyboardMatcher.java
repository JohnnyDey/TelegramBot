package com.vkbot.api.keyboard;

import command.InfoCommand;
import command.factory.CommandFactory;
import utils.KeyboardMap;

public class KeyboardMatcher {

    public static Keyboard match (KeyboardMap keyboardMap){
        switch (keyboardMap){
            case LIST:
                return createListMap();
            case CANCEL:
                return createCancelMap();
            case SET_TIME:
                return createSetTimeMap();
            case YES_OR_NO:
                return createYesNoMap();
            case PERSONS:
                return createPersonMap();
            case NEXT:
                return createNextMap();
        }
        return null;
    }

    private static Keyboard createNextMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.NEGATIVE, CommandFactory.CANCEL)
                .addToLine(0, Button.Color.POSITIVE, CommandFactory.NEXT);
    }

    private static Keyboard createPersonMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.PRIMARY, InfoCommand.Person.NASTYA.getName())
                .addToLine(1, Button.Color.NEGATIVE, CommandFactory.CANCEL);
    }

    private static Keyboard createSetTimeMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.DEFAULT, "5 минут")
                .addToLine(0, Button.Color.DEFAULT, "10 минут")
                .addToLine(0, Button.Color.DEFAULT, "30 минут")
                .addToLine(1, Button.Color.DEFAULT, "Час")
                .addToLine(1, Button.Color.DEFAULT, "Два час")
                .addToLine(1, Button.Color.DEFAULT, "6 часов")
                .addToLine(2, Button.Color.DEFAULT, "12 часов")
                .addToLine(2, Button.Color.DEFAULT, "Сутки")
                .addToLine(2, Button.Color.DEFAULT, "Два дня")
                .addToLine(3, Button.Color.DEFAULT, "Неделю")
                .addToLine(3, Button.Color.DEFAULT, "Две недели")
                .addToLine(3, Button.Color.DEFAULT, "Месяц")
                .addToLine(4, Button.Color.NEGATIVE, CommandFactory.CANCEL);
    }

    private static Keyboard createYesNoMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.POSITIVE, "Да")
                .addToLine(0, Button.Color.NEGATIVE, "Нет");
    }

    private static Keyboard createCancelMap() {
        return generalKeyboard().addToLine(0, Button.Color.NEGATIVE, CommandFactory.CANCEL);
    }

    private static Keyboard createListMap() {
        return generalKeyboard()
                .addToLine(0, Button.Color.PRIMARY, CommandFactory.INFO)
                .addToLine(0, Button.Color.PRIMARY, CommandFactory.SPAM)
                .addToLine(1, Button.Color.PRIMARY, CommandFactory.REMIND)
                .addToLine(1, Button.Color.PRIMARY, CommandFactory.MY_REMINDS);
    }

    private static Keyboard generalKeyboard(){
        Keyboard keyboard = new Keyboard(false);
        keyboard.setOneTime(true);
        return keyboard;
    }
}
