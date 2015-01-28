package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.models.Users;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ServiceGateIntegrationTest {
    private DatabaseProvider mockDB = mock(DatabaseProvider.class);
    private ServiceGate testServiceGate= new ServiceGate();
    private static final String TEST_LOGIN="login";
    private static final String TEST_PASSWORD="password";
    private static final String TEST_FIRST_NAME="firstName";
    private static final String TEST_LAST_NAME="lastName";
    Users testUser = new Users();
    @Before
    public void before(){
        mockDB.openConnection();
        testUser.setFirstName(TEST_LOGIN);
        testUser.setFirstName(TEST_PASSWORD);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setFirstName(TEST_LAST_NAME);
        testUser.saveIt();
    }
    @Features("Пользователи")
    @Stories("Проверка выдачи пользователя c Моком")
    @Test
    public void testGetUser() {
        String testUserString = testServiceGate.getUser(testUser.getUserId(), "");
        JsonObject testUserJSON = (JsonObject) new JsonParser().parse(testUserString);
        assertFalse("Error from Json : "+testUserJSON.get("error").getAsString(),testUserJSON.get("is_error").getAsBoolean());
        assertThat(testUserJSON.get("data"), Is.<JsonElement>is(ServiceGate.setJsonObject(testUser)));
    }
    @After
    public void after() {
        testUser.delete();
    }

}