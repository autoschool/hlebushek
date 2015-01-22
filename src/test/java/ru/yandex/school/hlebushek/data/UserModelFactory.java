package ru.yandex.school.hlebushek.data;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.school.hlebushek.models.Users;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Drak_kin on 22.01.2015.
 */
public class UserModelFactory extends TypeSafeMatcher<Users> {
    @Override
    protected boolean matchesSafely(Users item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {
            description.appendText("error");
    }
    public static List<Users> createUsersList(){


        return Arrays.asList(
                (Users)new Users().set("login", "admin").set("password", "admin").set("is_deleted",false),
                (Users)new Users().set("login", "testuser").set("password", "testpassword").set("first_name", "NameТест").set("last_name", "Lastname").set("is_deleted",false),
                (Users)new Users().set("login", "user1").set("password", "12qwerty").set("first_name", "Vasya").set("last_name", "Pupkin").set("is_deleted",false),
                (Users)new Users().set("login", "user2").set("password", "12345678").set("first_name", "Сергей").set("last_name", "Михайлович").set("is_deleted",false),
                (Users)new Users().set("login", "user3").set("password", "qwerty098").set("first_name", "Пётр").set("last_name", "Иванович").set("is_deleted",false)
        );
    }
}
