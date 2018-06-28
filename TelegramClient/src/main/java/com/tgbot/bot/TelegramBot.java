package com.tgbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import utils.TgDecider;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot{

    private Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Inject
    private TgDecider decider;

    @PostConstruct
    private void init(){
        logger.info("Bot started");
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            List<String> strings = decider.onText(update.getMessage());
            strings.forEach(s -> sendMessage(update.getMessage().getChatId(), s));
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
