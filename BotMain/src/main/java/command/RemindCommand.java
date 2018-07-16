package command;

import jpa.entity.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RemindCommand extends AbstractCommand {

    private ZonedDateTime zonedDateTime;

    @Override
    public List<Object> execute(String message, User user) {
        putPhases(phraseUtil.getTimerTime());
        return completeExecution();
    }

    @Override
    public List<Object> nextPhase(String message, User user) {
        if(zonedDateTime == null){
            zonedDateTime = parseDate(message);
            putPhases(phraseUtil.getTimerMsg());
        } else {
            startTimer(message, user);
            putPhases(phraseUtil.getSuccessTimedPhrase());
            return finishExecution();
        }
        return completeExecution();
    }

    private ZonedDateTime parseDate(String message) {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        switch (message){
            case "5 минут":
                return now.plusMinutes(5);
            case "10 минут":
                return now.plusMinutes(10);
            case "30 минут":
                return now.plusMinutes(30);
            case "Час":
                return now.plusHours(1);
            case "Два час":
                return now.plusHours(2);
            case "6 часов":
                return now.plusHours(1);
            case "12 часов":
                return now.plusHours(12);
            case "Сутки":
                return now.plusDays(1);
            case "Два дня":
                return now.plusDays(2);
            case "Неделю":
                return now.plusDays(7);
            case "Две недели":
                return now.plusDays(14);
            case "Месяц":
                return now.plusDays(Calendar.getInstance().getMaximum(Calendar.DAY_OF_MONTH));
            default:
                return now;
        }
    }

    private void startTimer(String msg, User user){
        timersService.startTimer(Date.from(zonedDateTime.toInstant()), user.getAppId(), msg);
    }

}
