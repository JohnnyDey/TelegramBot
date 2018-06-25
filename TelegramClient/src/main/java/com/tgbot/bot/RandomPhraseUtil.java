package com.tgbot.bot;

import com.tgbot.jpa.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class RandomPhraseUtil {

    private static List<String> phrases = Arrays.asList(
            "Я могу копать свой горшочек на протяжении нескольких минут. В январе я установил новый рекорд - 12 минут безпрерывного копания!",
            "Я начинающий блогер. На моем ютуб канале скоро выйдут про меня мультки. Я обязательно тебе скину ссылку.",
            "В детстве мои хозяева думали, что я девочка... но я не люблю об этом говорить",
            "Моё любимое развлечение: сидеть за диваном и неожиданно выпрыгивать на зазевавшихся прохожих. Если не считать развлечения обедом.",
            "Я очень не люблю дверной косяк. Однако пока мне не удалось выгнать его из дома прыжками.",
            "Мой любимый цвет: белый, любимое живтное: кот, любимое существо: я"
    );

    static String getRandomPhrase(){
        return phrases.get(new Random().nextInt(phrases.size()));
    }

    static String getFirstGreetPhrase(String name) {
        return "Привет, " + name + "! Меня зовут котик Оскар. Я пока не научился читать, " +
                "поэтому не пойму, что ты мне пишешь. Но зато я умею писать о себе" +
                "интересные факты! Круто, да?";
    }

    static String getSecondGreetPhrase() {
        return "Напиши мне что-нибудь, и я буду расценивать это так, что ты захотел" +
                "услышать от меня интересный факт обо мне :)";
    }

    static String getInfoPhrase(User user) {
        StringBuilder sb = new StringBuilder();
        if(user.getFirstName() != null) {
            sb.append("Твое имя: ").append(user.getFirstName()).append(".\n");
        }
        if(user.getLastName() != null) {
            sb.append("Фамилия у тебя: ").append(user.getLastName()).append(".\n");
        }
        if(user.getUserName() != null) {
            sb.append("В телеграме тебя знают, как ").append(user.getUserName()).append(".\n");
        }
        return sb.toString();
    }
}
