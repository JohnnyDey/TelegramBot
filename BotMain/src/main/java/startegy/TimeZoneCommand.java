package startegy;

import jpa.entity.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class TimeZoneCommand extends CommonCommand implements Command{
    @Override
    public List<Object> execute(String message, User user) {
        if(user.getTimeZone() != null){
            LocalTime now = LocalTime.now(ZoneOffset.of(user.getTimeZone()));
            phrases.add(phraseUtil.timeZoneExist(getFormattedTime(now)));
        } else {
            phrases.add(phraseUtil.emptyTimeZone());
        }
        return phrases;
    }

    private String getFormattedTime(LocalTime time){
        StringBuilder stringBuilder = new StringBuilder();
        if(time.getHour() < 10){
            stringBuilder.append("0");
        }
        stringBuilder.append(time.getHour()).append(":");
        if(time.getMinute() < 10){
            stringBuilder.append("0");
        }
        stringBuilder.append(time.getMinute());

        return stringBuilder.toString();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+0"));
        try {

            int delimiter = message.indexOf(":");
            int hour = Integer.parseInt(message.substring(0, delimiter));
            int min = Integer.parseInt(message.substring(delimiter + 1));
            if(hour > 23 || hour < 0 || min < 0 || min > 59){
                phrases.add(phraseUtil.badTimeZone());
            } else {
                hour -= now.getHour();
                min -= now.getMinute();
                ZoneOffset zoneOffset = getOffset(hour, min);
                user.setTimeZone(zoneOffset.getId());
                userServiceImp.saveUser(user);
                phrases.addAll(phraseUtil.ok());
                stop();
            }
        } catch (Exception e){
            phrases.add(phraseUtil.badTimeZone());
        }
        return phrases;
    }
    //разбораться с оффсетом в -12:30 и +12:30
    private ZoneOffset getOffset(int hour, int min){
        if(hour <= -12){
            hour += 24;
        } else if(hour > 12){
            hour -= 24;
        }
        if(hour > 0 && min < 0){
            hour--;
            min +=60;
        }
        if(hour < 0 && min > 0){
            hour++;
            min -= 60;
        }
        return ZoneOffset.ofHoursMinutes(hour, min);
    }
}
