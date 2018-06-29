package utils;

import com.sun.javafx.binding.StringFormatter;
import jpa.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class PhraseUtil {

    private Properties properties = new Properties();

    public PhraseUtil() throws IOException {
        InputStream resourceAsStream = PhraseUtil.class.getClassLoader().getResourceAsStream("core/phrases.properties");
        properties.load(new InputStreamReader(resourceAsStream));
    }

    private List<String> phrases = Arrays.asList(
            "Я могу копать свой горшочек на протяжении нескольких минут. В январе я установил новый рекорд - 12 минут безпрерывного копания!",
            "Я начинающий блогер. На моем ютуб канале скоро выйдут про меня мультки. Я обязательно тебе скину ссылку.",
            "В детстве мои хозяева думали, что я девочка... но я не люблю об этом говорить",
            "Моё любимое развлечение: сидеть за диваном и неожиданно выпрыгивать на зазевавшихся прохожих. Если не считать развлечения обедом.",
            "Я очень не люблю дверной косяк. Однако, пока мне не удалось выгнать его из дома прыжками.",
            "Мой любимый цвет: белый, любимое живтное: кот, любимое существо: я"
    );

    public String getRandomPhrase(){
        return phrases.get(new Random().nextInt(phrases.size()));
    }

    public String getFirstGreetPhrase(String name) {
        return String.format(properties.getProperty("greet.one"), name);
    }

    public String getSuccessTimedPhrase() {
        return properties.getProperty("timer.ok");
    }

    public String getFailesTimedPhrase(String form1, String form2) {
        return String.format(properties.getProperty("timer.fail"), form1, form2);
    }

    public String getSecondGreetPhrase() {
        return properties.getProperty("greet.two");
    }

    public String registered(String name){
        return String.format(properties.getProperty("register.ok"), name);
    }

    public String getInfoPhrase(User user) {
        StringBuilder sb = new StringBuilder();
        if(user.getTgName() != null) {
            sb.append(String.format(properties.getProperty("info.tg.name"), user.getTgName())).append("\n");
        }
        if(user.getVkName() != null) {
            sb.append(String.format(properties.getProperty("info.vk.name"), user.getVkName())).append("\n");
        }
        if(sb.length() == 0){
            sb.append(properties.getProperty("info.unknown"));
        }
        return sb.toString();
    }

    public String getHelp() {
        return properties.getProperty("help.list");
    }

    public String emptyName() {
        return properties.getProperty("register.empty.name");
    }
}
