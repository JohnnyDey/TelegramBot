package com.vkbot.api.rest;


import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.GET;

@Provider
@Path("/status")
public class Status {

    @GET
    public String getStatus(){
        return "ok";
    }

}
