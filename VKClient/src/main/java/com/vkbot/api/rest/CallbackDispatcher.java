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
import java.io.*;
import java.nio.charset.MalformedInputException;
import java.util.stream.Collectors;

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
//        try (BufferedReader reader = request.getReader()) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append('\n');
//            }
//        }

        request.setCharacterEncoding("UTF-8");
        Reader r = request.getReader();

        Writer w = new StringWriter();

        try {
            // Copy one character at a time
            int c = r.read();
            while (c != -1) {
                w.write(c);
                c = r.read();
            }
            w.close();
        } catch (MalformedInputException mie) {
        }

        String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("The message is " + sb.toString());
        logger.info("The string is " + test);
        logger.info(w.toString());
        callbackApiHandler.parse(sb.toString());

        return callbackApiHandler.getCallBack();
    }


}
