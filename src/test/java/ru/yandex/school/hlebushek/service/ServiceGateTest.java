package ru.yandex.school.hlebushek.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

import static org.junit.Assert.assertNotNull;

public class ServiceGateTest {
    @BeforeClass
    public static void prepareClass(){
        DatabaseProvider.openConnection();
    }
    ServiceGate testServiceGate;
    @Before
    public void prepare(){
        testServiceGate = new ServiceGate();
    }

    @Test
    public void GetUserTest() throws Exception {
        String user1 = testServiceGate.getUser(1,"");
        assertNotNull(user1);
    }
}