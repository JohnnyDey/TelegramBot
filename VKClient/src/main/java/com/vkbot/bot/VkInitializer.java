package com.vkbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


@Startup
@Singleton
public class VkInitializer {

    private Logger logger = LoggerFactory.getLogger(VkInitializer.class);

    @Inject
    private VkBot bot;

    @PostConstruct
    private void start(){
        logger.info("Starting");
        //com.vkbot.bot.VkBot bot = new com.vkbot.bot.VkBot("fdb389473d33ef3075b8c3185c32b8c11fdeee66f7ac22e5dc574790d9965ddb5b531df6ef94e9653a75b");
        bot.setHandler();
    }
}
