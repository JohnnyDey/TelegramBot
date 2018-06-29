package startegy;

import jpa.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RemindCommand extends CommonCommand implements Command {

    private final SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public static final String ID_ARG = "id";

    @Override
    public List<String> execute(String message, User user) {
        if(startTimer(message, (Long) args.get(ID_ARG))){
            phrases.add(phraseUtil.getSuccessTimedPhrase());
        }else {
            phrases.add(phraseUtil.getFailesTimedPhrase(formatter1.toPattern(), formatter2.toPattern()));
        }
        return phrases;
    }

    private boolean startTimer(String msg, Long to){
        msg = msg.replace("/remind", "").trim();
        int spaceIndex = formatter1.toPattern().length();
        if(msg.length() <= spaceIndex) return false;

        String date = msg.substring(0, spaceIndex);
        Date formattedDate;
        synchronized (formatter1) {
            try {
                formattedDate = formatter1.parse(date);
            } catch (ParseException e) {
                synchronized (formatter2) {
                    try {
                        formattedDate = formatter2.parse(date);
                    } catch (ParseException e1) {
                        return false;
                    }
                }
            }
        }

        timersService.startTimer(formattedDate, to, msg.substring(spaceIndex + 1));
        return true;
    }
}
