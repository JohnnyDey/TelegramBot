package utils;

import jpa.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PhraseUtil implements Serializable {

    private final Properties properties = new Properties();

    public PhraseUtil() throws IOException {
        InputStream resourceAsStream = PhraseUtil.class.getClassLoader().getResourceAsStream("core/phrases.properties");
        properties.load(new InputStreamReader(resourceAsStream));
    }

    public List<Object> timeZoneEmpty() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.timezone.empty"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.pleased);
        return list;
    }

    public String badTimeZone() {
        return properties.getProperty("timezone.bad");
    }

    public List<Object> wrongTimeFormat(Object form1, Object form2, Object form3) {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.fail.one"));
        list.add(StickerCollector.feelsBad);
        list.add(String.format(properties.getProperty("timer.format"), form1, form2, form3));
        return list;
    }

    public List<Object> getTimerTime(Object form1, Object form2, Object form3) {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.get-time"));
        list.add(KeyboardMap.SET_TIME);
        list.add(String.format(properties.getProperty("timer.format"), form1, form2, form3));
        return list;
    }

    public List<Object> getTimerMsg() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.get-time"));
        list.add(KeyboardMap.CANCEL);
        list.add(properties.getProperty("timer.get-msg"));
        return list;
    }

    public List<Object> getSuccessTimedPhrase() {
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.LIST);
        list.add(properties.getProperty("timer.ok"));
        list.add(StickerCollector.thumbUp);
        return list;
    }

    public List<Object> emptyTimeZone(){
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.SET_TIME);
        list.add(properties.getProperty("timezone.empty"));
        return list;
    }

    public List<Object> timeZoneExist(String time){
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.SET_TIME);
        list.add(String.format(properties.getProperty("timezone.exist"), time));
        return list;
    }


    public List<Object> getInfoPhrase(User user) {
        List<Object> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(user.getUserName() != null) {
            sb.append(String.format(properties.getProperty("info.name"), user.getUserName())).append("\n");
        }
        list.add(sb.toString());
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.glad);
        return list;
    }

    public List<Object> getHelp() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("help.list"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.relax);
        return list;
    }

    public List<Object> notifyNo(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.no"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.sad);
        return list;
    }

    public List<Object> notifyYes(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.yes"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.thumbUp);
        return list;
    }

    public String howToNotify() {
        return properties.getProperty("notify.disclaimer");
    }

    public List<Object> getNotifyHelp(String onOrOffString) {
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.YES_OR_NO);
        list.add(String.format(properties.getProperty("notify.help"), onOrOffString));
        return list;
    }

    public List<Object> askToStartNotify() {
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.YES_OR_NO);
        list.add(properties.getProperty("notify.ask.start"));
        return list;
    }

    public List<Object> askToStopNotify() {
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.YES_OR_NO);
        list.add(properties.getProperty("notify.ask.stop"));
        return list;
    }

    public List<Object> notifyNoChanges() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.no-changes"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.pleased);
        return list;
    }

    public List<Object> ok(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("common.ok"));
        list.add(KeyboardMap.LIST);
        list.add(StickerCollector.thumbUp);
        return list;
    }

}
