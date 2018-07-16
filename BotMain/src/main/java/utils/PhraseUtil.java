package utils;

import jpa.entity.TimerId;
import jpa.entity.User;

import javax.ejb.Timer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

public class PhraseUtil implements Serializable {

    private final Properties properties = new Properties();

    public PhraseUtil() throws IOException {
        InputStream resourceAsStream = PhraseUtil.class.getClassLoader().getResourceAsStream("core/phrases.properties");
        properties.load(new InputStreamReader(resourceAsStream));
    }

    public List<Object> getTimerTime() {
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.SET_TIME);
        list.add(properties.getProperty("timer.get-time"));
        return list;
    }

    public List<Object> getTimerMsg() {
        List<Object> list = new ArrayList<>();
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

    public List<Object> toBigInteger(){
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.CANCEL);
        list.add(properties.getProperty("timers.out-of-bounds"));

        return list;
    }

    public List<Object> noTimers(){
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.LIST);
        list.add(properties.getProperty("timers.list.empty"));
        return list;
    }

    public List<Object> timersList(List<Timer> timers){
        List<Object> list = new ArrayList<>();
        list.add(KeyboardMap.CANCEL);
        StringBuilder sb = new StringBuilder(properties.getProperty("timers.list"));
        for(int i = 1; i <= timers.size(); i++){
            sb.append(formatDate(i, timers.get(i - 1)));
        }
        list.add(sb.toString());
        return list;
    }

    private String formatDate(int index, Timer t){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(t.getNextTimeout());
        return "\n" + index + ". " + calendar.get(Calendar.DAY_OF_MONTH) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR) +
                " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) +
                " Текст: " + ((TimerId) t.getInfo()).getMsg();
    }
}
