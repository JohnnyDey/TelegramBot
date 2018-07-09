package startegy;

import jpa.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

public class RemindCommand extends CommonCommand implements Command {

    private static final String form = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(form.replaceAll("[.]", "/"));
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(form);
    private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern(form.replaceAll("[.]", "-"));

    private LocalDateTime localDateTime;

    @Override
    public List<Object> execute(String message, User user) {
        if(user.getTimeZone() == null){
            phrases.addAll(phraseUtil.timeZoneEmpty());
            return phrases;
        } else {
            phrases.addAll(phraseUtil.getTimerTime(form.replaceAll("[.]", "/"), form, form.replaceAll("[.]", "-")));
        }
        phrases.addAll(phraseUtil.getTimerTime(form.replaceAll("[.]", "/"), form, form.replaceAll("[.]", "-")));
        return phrases;
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
          if(localDateTime == null){
            try {
                phrases.add(phraseUtil.getTimerMsg());
                localDateTime = LocalDateTime.parse(message, formatter1);
            } catch (DateTimeParseException e) {
                try {
                    localDateTime = LocalDateTime.parse(message, formatter2);
                } catch (DateTimeParseException e1) {
                    try {
                        localDateTime = LocalDateTime.parse(message, formatter3);
                    } catch (DateTimeParseException e2) {
                        return phraseUtil.wrongTimeFormat(form.replaceAll("[.]", "/"), form, form.replaceAll("[.]", "-"));
                    }
                }
            }
        } else {
            startTimer(message, user);
            phrases.addAll(phraseUtil.getSuccessTimedPhrase());
        }
        return phrases;
    }

    private void startTimer(String msg, User user){
        stop();
        timersService.startTimer(zonedDateToDate(localDateTime, String.valueOf(user.getTimeZone())), user.getAppId(), msg);
    }

    private Date zonedDateToDate(LocalDateTime localDateTime, String timeZone){
        return Date.from(localDateTime.atZone(ZoneId.of("UTC"+timeZone)).toInstant());
    }
}
