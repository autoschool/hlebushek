package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

import static org.junit.Assert.*;
import static ru.yandex.school.hlebushek.service.UsersData.getUser;

public class UsersDataTest {

    @Before
    public void setUp() throws Exception {
        DatabaseProvider.openConnection();
    }

    @Test
    public void GetUserTest() throws Exception {
        JsonElement test_user= getUser(1,"");
        assertNotNull("Админ не получен",test_user);
    }
    @Test
    public void GetUserTest2() throws Exception {
        JsonElement test_user= getUser(0,"user1");
        assertNotNull("user1 не получен",test_user);
    }

}