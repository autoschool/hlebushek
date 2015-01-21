package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


public class ServiceGateUnitTest extends JerseyTest {
    @BeforeClass
    public static void prepare(){
        DatabaseProvider.openConnection();
    }
    @Override
    protected Application configure() {
        ResourceConfig resourceConfig;
        resourceConfig = new ResourceConfig(ServiceGate.class);
        resourceConfig.register(DatabaseProvider.class);
        return resourceConfig;
    }

    @Test
    public void testGetUser() {
        Response response = target("users").queryParam("user_id", 1).request().get();
        assertEquals(200, response.getStatus());
        assertTrue(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE));
        JsonObject recivedJSON = null;
        try{
            recivedJSON = (JsonObject) new JsonParser().parse(response.readEntity(String.class));
        }catch (Exception ex){
            fail("can't to parse result");
        }
        assertFalse(recivedJSON.isJsonNull());
        assertTrue(recivedJSON.has("is_error"));
        assertTrue(recivedJSON.has("error"));
        assertFalse("Error from Json : "+recivedJSON.get("error").getAsString(),recivedJSON.get("is_error").getAsBoolean());
        assertTrue(recivedJSON.has("data"));
        assertFalse(recivedJSON.get("data").isJsonNull());
    }
}