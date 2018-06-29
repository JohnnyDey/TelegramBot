package com.tgbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Initializer {

    private Logger logger = LoggerFactory.getLogger(Initializer.class);

    @PostConstruct
    private void start(){
        logger.info("Telegram starting");
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot());
            logger.info("Telegram started");
        } catch (TelegramApiException e) {
            logger.error("Failed! Cause: " + e.getMessage());
        }
    }
}
