package bot;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.Serializable;


public class MyBot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if("/start".equals(update.getMessage().getText())){
                sendMessage(update.getMessage().getChatId(), RandomFraserUtil.getFirstGreetFrase());
                sendMessage(update.getMessage().getChatId(), RandomFraserUtil.getSecondGreetFrase());
            } else {
                sendMessage(update.getMessage().getChatId(), RandomFraserUtil.getRandomFrase());
            }
        }
    }

    public String getBotUsername() {
        return "OscarTheCatBot";
    }

    public String getBotToken() {
        return "528645284:AAGEoocav3fgUKqw5Tum2AJuP52lF0QB8-4";
    }


    private void sendMessage(Long id, String msg){
        SendMessage message = new SendMessage()
                .setChatId(id)
                .setText(msg);
        send(message);
    }

    private <T extends Serializable> void send(BotApiMethod<T> method){
        try {
            execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
