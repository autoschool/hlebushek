package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.models.Users;

import static org.junit.Assert.*;
import static ru.yandex.school.hlebushek.service.ServiceResult.setJsonObject;
import static ru.yandex.school.hlebushek.service.UsersData.getUser;

public class UsersDataTest {
    private static final String TEST_LOGIN="login";
    private static final String TEST_PASSWORD="password";
    private static final String TEST_FIRST_NAME="firstName";
    private static final String TEST_LAST_NAME="lastName";
    Users testUser = new Users();
    @Before
    public void before() throws Exception {
        DatabaseProvider.openConnection();
        testUser.setFirstName(TEST_LOGIN);
        testUser.setFirstName(TEST_PASSWORD);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setFirstName(TEST_LAST_NAME);
        testUser.saveIt();
    }

    @Test
    public void getUserTest() throws Exception {
        JsonElement testUserJSON= getUser(testUser.getUserId(),"");
        assertThat(testUserJSON, Is.<JsonElement>is(setJsonObject(testUser)));
    }
    @After
    public void after() {
        testUser.delete();
    }

}