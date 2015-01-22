package ru.yandex.school.hlebushek.matchers;

import ru.yandex.qatools.matchers.collection.Wrapper;
import ru.yandex.school.hlebushek.models.Users;

/**
 * Created by Drak_kin on 22.01.2015.
 */
public class UserWrapper extends Wrapper<Users> {

    @Override
    public boolean safelyEquals(Users actual, Users expected) {
        for(String attr : Users.attributes()){
            if ("user_id".equals(attr)) continue;
            if ("is_deleted".equals(attr)) continue;
            if(expected.get(attr) != null) {
                if (!expected.get(attr).equals(actual.get(attr))) {
                    return false;
                }
            }
            else
            if (actual.get(attr) != null) {
                return false;
            }

        }
        return true;
    }

    @Override
    public String asString(Users obj) {
        return obj.toString();
    }
}
