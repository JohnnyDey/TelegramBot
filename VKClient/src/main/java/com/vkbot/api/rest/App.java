package com.vkbot.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class App extends Application {

    private Logger logger = LoggerFactory.getLogger(App.class);

    public App() {
        logger.info("Rest is up");
    }
}
