package ru.yandex.school.hlebushek.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SomeData {

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{ \"message\" : \"get test json\"}";
    }
}
