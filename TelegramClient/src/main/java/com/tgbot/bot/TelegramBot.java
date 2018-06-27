package com.tgbot.bot;

import jpa.entity.User;
import jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;


public class TelegramBot extends TelegramLongPollingBot{

    private Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Inject
    private UserService userServiceImp;

    @PostConstruct
    private void init(){
        logger.info("Bot started");
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            if("/start".equals(message.getText())){
                registerIfNeed(message.getChat());

                sendMessage(message.getChatId(), RandomPhraseUtil.getFirstGreetPhrase(message.getChat().getUserName()));
                sendMessage(message.getChatId(), RandomPhraseUtil.getSecondGreetPhrase());
            } else if("/add".equals(message.getText())) {
                registerIfNeed(message.getChat());
            } else if("/get".equals(message.getText())) {
                User user = userServiceImp.getUser(message.getChat().getId());
                sendMessage(message.getChatId(), RandomPhraseUtil.getInfoPhrase(user));
            } else {
                sendMessage(message.getChatId(), RandomPhraseUtil.getRandomPhrase());
            }
        }
    }

    private void registerIfNeed(Chat chat){
        User user = userServiceImp.getUser(chat.getId());
        if(user == null){
            userServiceImp.saveUser(chat.getId(), chat.getFirstName(), chat.getLastName(), chat.getUserName());
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
