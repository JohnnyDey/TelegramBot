package com.vkbot.api.rest;

import com.vkbot.api.messages.CallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.IOException;

@Provider
@Path("/callback")
public class CallbackDispatcher extends Application {

    @Inject
    private CallbackHandler callbackApiHandler;

    private Logger logger = LoggerFactory.getLogger(App.class);

    @GET
    public Response get(@Context HttpServletRequest request) throws IOException {
        logger.info("got a message" );
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        logger.info("The message is " + sb.toString());
        callbackApiHandler.parse(sb.toString());

        return Response.ok().build();
    }
}
