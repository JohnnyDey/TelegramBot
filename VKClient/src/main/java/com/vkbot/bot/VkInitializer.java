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
        logger.info("Vk starting");
        bot.setHandler();
        logger.info("Vk started");
    }
}
