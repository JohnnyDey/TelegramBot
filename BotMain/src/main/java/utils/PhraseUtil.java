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
        list.add(StickerCollector.pleased);
        return list;
    }

    public String badTimeZone() {
        return properties.getProperty("timezone.bad");
    }

    public String howToName(String userName) {
        return properties.getProperty("register.how-to-name");
    }

    public List<Object> wrongTimeFormat(Object form1, Object form2, Object form3) {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.fail.one"));
        list.add(StickerCollector.feelsBad);
        list.add(String.format(properties.getProperty("timer.format"), form1, form2, form3));
        return list;
    }

    public List<String> getTimerTime(Object form1, Object form2, Object form3) {
        List<String> list = new ArrayList<>();
        list.add(properties.getProperty("timer.get-time"));
        list.add(String.format(properties.getProperty("timer.format"), form1, form2, form3));
        return list;
    }

    public String getTimerMsg() {
        return properties.getProperty("timer.get-msg");
    }

    public List<Object> getSuccessTimedPhrase() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("timer.ok"));
        list.add(StickerCollector.thumbUp);
        return list;
    }

    public String emptyTimeZone(){
        return properties.getProperty("timezone.empty");
    }

    public String timeZoneExist(String time){
        return String.format(properties.getProperty("timezone.exist"), time);
    }

    public String setTimeZone(){
        return properties.getProperty("timezone.time");
    }

    public List<Object> registered(Object name){
        List<Object> list = new ArrayList<>();
        list.add(String.format(properties.getProperty("register.ok"), name));
        list.add(StickerCollector.nice);
        return list;
    }

    public List<Object> emptyName() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("register.empty.name"));
        list.add(StickerCollector.oops);
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

    public String howToNotify() {
        return properties.getProperty("notify.disclaimer");
    }

    public String getNotifyHelp(String onOrOffString) {
        return String.format(properties.getProperty("notify.help"), onOrOffString);
    }

    public String askToStartNotify() {
        return properties.getProperty("notify.ask.start");
    }

    public Object askToStopNotify() {
        return properties.getProperty("notify.ask.stop");
    }

    public List<Object> notifyNoChanges() {
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("notify.no-changes"));
        list.add(StickerCollector.pleased);
        return list;
    }

    public List<Object> ok(){
        List<Object> list = new ArrayList<>();
        list.add(properties.getProperty("common.ok"));
        list.add(StickerCollector.thumbUp);
        return list;
    }



}
