package ru.yandex.school.hlebushek.models;

import org.junit.Before;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UsersTest {

    @Before
    public void setUp() throws Exception {
        DatabaseProvider.openConnection();
    }

    @Test
    public void testGetUserIdAdminTest() throws Exception {
        Users admin = Users.findById(1),expected = new Users();
        expected.setLogin("admin");
        expected.setPassword("admin");
        expected.setUserIsDeleted(false);
        assertFalse("Администратор не создан", admin == null);
        for(String attrib:  Users.attributes()){
            if(admin.get(attrib) == null) continue;
            if(attrib.equals("user_id")) continue;
            assertEquals("Аттрибут "+attrib+" различен",expected.get(attrib),admin.get(attrib));
        }
    }
    @Test
    public void testGetUserIdUsersTest() throws Exception {
        Map<String,String>[] usersData = new Map[4];

        for (int i=0;i<4;i++)
            usersData[i] = new HashMap<>();

        usersData[0].put("login","testuser");
        usersData[0].put("password","testpassword");
        usersData[0].put("first_name","NameТест");
        usersData[0].put("last_name","Lastname");
        usersData[0].put("is_deleted","false");

        usersData[1].put("login","user1");
        usersData[1].put("password","12qwerty");
        usersData[1].put("first_name","Vasya");
        usersData[1].put("last_name","Pupkin");
        usersData[1].put("is_deleted", "false");

        usersData[2].put("login","user2");
        usersData[2].put("password","12345678");
        usersData[2].put("first_name","Сергей");
        usersData[2].put("last_name","Михайлович");
        usersData[2].put("is_deleted", "false");

        usersData[3].put("login","user3");
        usersData[3].put("password","qwerty098");
        usersData[3].put("first_name","Пётр");
        usersData[3].put("last_name","Иванович");
        usersData[3].put("is_deleted", "false");

        for(int i=2;i<6;i++) {
            Users user = Users.findById(i);
            assertFalse("Пользователь "+Integer.toString(i - 1)+" не в базе",user == null);
            for (String attrib : Users.attributes()) {
                if (user.get(attrib) == null) continue;
                if (attrib.equals("user_id")) continue;
                assertEquals("Аттрибут " + attrib + " у пользователя " +Integer.toString(i - 1)+ " различен", usersData[i-2].get(attrib).toString(), user.get(attrib).toString());
            }
        }
    }

}