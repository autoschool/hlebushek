package ru.yandex.school.hlebushek.matchers;

import ru.yandex.qatools.matchers.collection.Wrapper;
import ru.yandex.qatools.matchers.collection.WrapperFactory;
import ru.yandex.school.hlebushek.models.Users;

/**
 * Created by Drak_kin on 22.01.2015.
 */
public class UserWrapperFactory implements WrapperFactory<Users> {
    @Override
    public Wrapper<Users> newWrapper() {
        return new UserWrapper();
    }
}
