package ru.yandex.school.hlebushek.models;

import org.junit.*;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static ru.yandex.school.hlebushek.matchers.UsersMatcher.same;

public class UsersTest {
    Users testUser = new Users();
    private static final String TEST_LOGIN="login";
    private static final String TEST_PASSWORD="password";
    private static final String TEST_FIRST_NAME="firstName";
    private static final String TEST_LAST_NAME="lastName";
    Collection<Users> allActualUsers = Users.findAll();
    @BeforeClass
    public static void setUp() throws Exception {
        DatabaseProvider.openConnection();
    }
    @Before
    public void prepare(){
        testUser.setLogin(TEST_LOGIN);
        testUser.setPassword(TEST_PASSWORD);
        testUser.setFirstName(TEST_FIRST_NAME);
        testUser.setLastName(TEST_LAST_NAME);
        testUser.saveIt();
    }
    @Features("Пользователи")
    @Stories("Проверка модели")
    @Test
    public void testGetUsersTest() throws Exception {
        assertThat(allActualUsers, hasItem(same(testUser)));
    }
    @After
    public void after() {
        testUser.delete();
    }


}