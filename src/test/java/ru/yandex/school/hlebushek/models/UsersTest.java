package ru.yandex.school.hlebushek.models;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.school.hlebushek.data.UserModelFactory;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.matchers.UserWrapperFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;

public class UsersTest {
    private List<Users> allExpectedUsers = UserModelFactory.createUsersList();
    private List<Users> allActualUsers = Users.findAll();
    @BeforeClass
    public static void setUp() throws Exception {
        DatabaseProvider.openConnection();
    }

    @Test
    public void testGetUsersTest() throws Exception {
        assertThat(allExpectedUsers, hasSameItemsAsList(allActualUsers).useWrapperFactory(new UserWrapperFactory()));
    }




}