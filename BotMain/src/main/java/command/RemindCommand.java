package command;

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
            putPhases(phraseUtil.timeZoneEmpty());
            return finishExecution();
        }
        putPhases(phraseUtil.getTimerTime(form.replaceAll("[.]", "/"), form, form.replaceAll("[.]", "-")));
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(localDateTime == null){
            try {
                putPhase(phraseUtil.getTimerMsg());
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
            putPhases(phraseUtil.getSuccessTimedPhrase());
            return finishExecution();
        }
        return completeExecution();
    }

    private void startTimer(String msg, User user){
        timersService.startTimer(zonedDateToDate(localDateTime, String.valueOf(user.getTimeZone())), user.getAppId(), msg);
    }

    private Date zonedDateToDate(LocalDateTime localDateTime, String timeZone){
        return Date.from(localDateTime.atZone(ZoneId.of("UTC"+timeZone)).toInstant());
    }
}
