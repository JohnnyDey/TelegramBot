package utils;

import jpa.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PhraseUtil {

    private Properties properties = new Properties();

    public PhraseUtil() throws IOException {
        InputStream resourceAsStream = PhraseUtil.class.getClassLoader().getResourceAsStream("core/phrases.properties");
        properties.load(new InputStreamReader(resourceAsStream));
    }

    private List<Object> phrases = Arrays.asList(
            "Я могу копать свой горшочек на протяжении нескольких минут. В январе я установил новый рекорд - 12 минут безпрерывного копания!",
            "Я начинающий блогер. На моем ютуб канале скоро выйдут про меня мультки. Я обязательно тебе скину ссылку.",
            "В детстве мои хозяева думали, что я девочка... но я не люблю об этом говорить",
            "Моё любимое развлечение: сидеть за диваном и неожиданно выпрыгивать на зазевавшихся прохожих. Если не считать развлечения обедом.",
            "Я очень не люблю дверной косяк. Однако, пока мне не удалось выгнать его из дома прыжками.",
            "Мой любимый цвет: белый, любимое живтное: кот, любимое существо: я"
    );

    public Object getRandomPhrase(){
        return phrases.get(new Random().nextInt(phrases.size()));
    }

    public List<Object> getGreetPhrases(Object name) {
        List<Object> list = new ArrayList<>();
        list.add(String.format(properties.getProperty("greet.one"), name));
        list.add(properties.getProperty("greet.two"));
        list.add(StickerCollector.smile);
        return list;
    }

    public List<Object> getSuccessTimedPhrase() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.ok"));
        list.add(StickerCollector.thumbUp);
        return list;
    }

    public List<Object> getFailedTimedPhrase(Object form1, Object form2, Object form3) {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.fail.one"));
        list.add(StickerCollector.feelsBad);
        list.add(String.format(properties.getProperty("timer.fail.two"), form1, form2, form3));
        list.add(properties.getProperty("timer.fail.three"));
        return list;

    }

    public List<Object> registered(Object name){
        List<Object> list = new ArrayList<>();
        list.add(String.format(properties.getProperty("register.ok"), name));
        list.add(StickerCollector.nice);
        return list;
    }

    public List<Object> getInfoPhrase(User user) {
        List<Object> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(user.getUserName() != null) {
            sb.append(String.format(properties.getProperty("info.name"), user.getUserName())).append("\n");
        }
        if(sb.length() == 0){
            list.add(properties.getProperty("info.unknown.one"));
            list.add(StickerCollector.anonymous);
            list.add(properties.getProperty("info.unknown.two"));
            list.add(properties.getProperty("info.unknown.three"));
        } else {
            list.add(sb.toString());
            list.add(StickerCollector.glad);
        }
        return list;
    }

    public List<Object> getHelp() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("help.list"));
        list.add(StickerCollector.relax);
        return list;
    }

    public List<Object> emptyName() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("register.empty.name"));
        list.add(StickerCollector.oops);
        return list;
    }

    public List<Object> notifyNo(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.no"));
        list.add(StickerCollector.sad);
        return list;
    }

    public List<Object> notifyYes(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.yes"));
        list.add(StickerCollector.thumbUp);
        return list;
    }

    public List<Object> ok(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("common.ok"));
        list.add(StickerCollector.thumbUp);
        return list;
    }


    public String howToNotify() {
        return properties.getProperty("notify.disclaimer");
    }

    public String getNotifyHelp() {
        return properties.getProperty("notify.help");
    }

    public List<Object> timeZoneEmpty() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.timezone.empty"));
        list.add(StickerCollector.pleased);
        return list;
    }

    public String badTimeZone() {
        return properties.getProperty("timer.timezone.bad");
    }
}
