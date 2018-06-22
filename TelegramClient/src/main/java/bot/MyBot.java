package bot;

import jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import jpa.service.UserService;

import java.io.Serializable;


public class MyBot extends TelegramLongPollingBot{

    @Autowired
    private UserService userServiceImp;

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            if("/start".equals(message.getText())){
                registerIfNeed(message.getChat());

                sendMessage(message.getChatId(), RandomFraserUtil.getFirstGreetFrase(message.getChat().getUserName()));
                sendMessage(message.getChatId(), RandomFraserUtil.getSecondGreetFrase());
            } else {
                sendMessage(message.getChatId(), RandomFraserUtil.getRandomFrase());
            }
        }
    }

    private void registerIfNeed(Chat chat){
        User user = userServiceImp.getUser(chat.getId());
        if(user == null){
            user = new User();
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setTelegramId(chat.getId());
            userServiceImp.saveUser(user);
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
