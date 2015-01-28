package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static ru.yandex.school.hlebushek.service.ServiceResult.setJsonObject;


public class ServiceGateJerseyTest extends JerseyTest {

    @BeforeClass
    public static void prepare(){
        DatabaseProvider.openConnection();
    }
    private static final String TEST_LOGIN="login";
    private static final String TEST_PASSWORD="password";
    private static final String TEST_FIRST_NAME="firstName";
    private static final String TEST_LAST_NAME="lastName";
    Users testUser = new Users();
    @Before
    public void before(){
        testUser.setFirstName(TEST_LOGIN);
        testUser.setFirstName(TEST_PASSWORD);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setFirstName(TEST_LAST_NAME);
        testUser.saveIt();
    }
    @Override
    protected Application configure() {
        ResourceConfig resourceConfig;
        resourceConfig = new ResourceConfig(ServiceGate.class);
        resourceConfig.register(DatabaseProvider.class);
        return resourceConfig;
    }
    @Features("Пользователи")
    @Stories("Проверка выдачи пользователя c JerseyTest")
    @Test
    public void testGetUser() {
        Response response = target("users").queryParam("user_id", testUser.getId()).request().get();
        assertEquals(200, response.getStatus());
        assertTrue(response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE));
        JsonObject receivedJSON = null;
        try{
            receivedJSON = (JsonObject) new JsonParser().parse(response.readEntity(String.class));
        }catch (Exception ex){
            fail("can't to parse result");
        }
        assertFalse(receivedJSON.isJsonNull());
        assertTrue(receivedJSON.has("is_error"));
        assertTrue(receivedJSON.has("error"));
        assertFalse("Error from Json : "+receivedJSON.get("error").getAsString(),receivedJSON.get("is_error").getAsBoolean());
        assertTrue(receivedJSON.has("data"));
        assertThat(receivedJSON.get("data"), Is.<JsonElement>is(setJsonObject(testUser)));

    }
    @After
    public void after() {
        testUser.delete();
    }
}