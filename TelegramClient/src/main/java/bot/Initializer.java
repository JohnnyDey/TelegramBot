package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;

@Startup
public class Initializer {

    @PostConstruct
    public void init(){
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
