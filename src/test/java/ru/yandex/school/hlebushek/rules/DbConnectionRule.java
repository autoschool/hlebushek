package ru.yandex.school.hlebushek.rules;


import org.junit.rules.ExternalResource;
import ru.yandex.school.hlebushek.db.DatabaseProvider;

/**
 * Created by ksenie on 23.01.15.
 */
public class DbConnectionRule extends ExternalResource {

    @Override
    public void before() {
        DatabaseProvider.openConnection();
    }

    @Override
    public void after() {
        DatabaseProvider.closeConnection();
    }


}
