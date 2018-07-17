package com.vkbot.api.rest;

import com.vkbot.api.messages.CallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

@Provider
@Path("/callback")
public class CallbackDispatcher extends Application {

    @Inject
    private CallbackHandler callbackApiHandler;

    private Logger logger = LoggerFactory.getLogger(App.class);

    @POST
    public String get(@Context HttpServletRequest request) throws IOException {
        logger.info("got a message" );
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        String decode = URLDecoder.decode(sb.toString(), "WINDOWS-1251");
        logger.info("The message is " + decode);
        callbackApiHandler.parse(decode);

        return callbackApiHandler.getCallBack();
    }
}
