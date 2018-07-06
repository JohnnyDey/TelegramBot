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


    public static final String ID_ARG = "id";

    @Override
    public List<Object> execute(String message, User user) {

        if(startTimer(message, (Long) args.get(ID_ARG))){
            phrases.addAll(phraseUtil.getSuccessTimedPhrase());
        }else {
            phrases.addAll(phraseUtil.getFailedTimedPhrase(form.replaceAll("[.]", "/"), form, form.replaceAll("[.]", "-")));
        }
        return phrases;
    }

    private boolean startTimer(String msg, Long to){
        msg = msg.replace("/remind", "").trim();
        int spaceIndex = form.length();
        if(msg.length() <= spaceIndex) return false;

        String date = msg.substring(0, spaceIndex);
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException e) {
            try {
                localDateTime = LocalDateTime.parse(date, formatter2);
            } catch (DateTimeParseException e1) {
                try {
                    localDateTime = LocalDateTime.parse(date, formatter3);
                } catch (DateTimeParseException e2) {
                    return false;
                }
            }
        }

        timersService.startTimer(zonedDateToDate(localDateTime), to, msg.substring(spaceIndex + 1));
        return true;
    }

    private Date zonedDateToDate(LocalDateTime localDateTime){
        //ZoneId.of("UTC+0");
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
